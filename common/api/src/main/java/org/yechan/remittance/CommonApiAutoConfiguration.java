package org.yechan.remittance;

import java.util.List;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AutoConfiguration
@Import(GlobalExceptionHandler.class)
public class CommonApiAutoConfiguration implements WebMvcConfigurer {

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(loginUserIdArgumentResolver());
  }

  @Bean
  LoginUserIdArgumentResolver loginUserIdArgumentResolver() {
    return new LoginUserIdArgumentResolver();
  }
}
