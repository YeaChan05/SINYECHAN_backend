package org.yechan.remittance.account;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationUseCase {

  SseEmitter connectRegister(Long memberId);
}

class NotificationSessionRegistry implements NotificationUseCase {

  private final Map<Long, SseEmitter> sessions = new ConcurrentHashMap<>();
  private final Supplier<SseEmitter> emitterSupplier;

  NotificationSessionRegistry() {
    this(SseEmitter::new);
  }

  NotificationSessionRegistry(Supplier<SseEmitter> emitterSupplier) {
    this.emitterSupplier = emitterSupplier;
  }

  @Override
  public SseEmitter connectRegister(Long memberId) {
    var emitter = emitterSupplier.get();
    sessions.put(memberId, emitter);
    emitter.onCompletion(() -> sessions.remove(memberId));
    emitter.onTimeout(() -> sessions.remove(memberId));
    emitter.onError(error -> sessions.remove(memberId));
    return emitter;
  }

  Optional<SseEmitter> find(Long memberId) {
    return Optional.ofNullable(sessions.get(memberId));
  }

  boolean push(Long memberId, Object payload) {
    var emitter = sessions.get(memberId);
    if (emitter == null) {
      return false;
    }
    try {
      emitter.send(payload);
      return true;
    } catch (IOException | IllegalStateException e) {
      sessions.remove(memberId);
      return false;
    }
  }
}
