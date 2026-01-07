package org.yechan.remittance;

import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

class LoginUserIdArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(LoginUserId.class)
        && (Long.class.equals(parameter.getParameterType())
        || long.class.equals(parameter.getParameterType()));
  }

  @Override
  public Object resolveArgument(
      MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory
  ) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
      throw new BusinessException(Status.AUTHENTICATION_FAILED, "Unauthorized");
    }

    String subject = authentication.getName();
    try {
      return Long.parseLong(subject);
    } catch (NumberFormatException ex) {
      throw new BusinessException(Status.BAD_REQUEST, "Invalid user id");
    }
  }
}
