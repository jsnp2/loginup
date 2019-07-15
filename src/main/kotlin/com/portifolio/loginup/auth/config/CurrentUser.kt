package com.portifolio.loginup.auth.config

import org.springframework.security.core.annotation.AuthenticationPrincipal

@Target(AnnotationTarget.TYPE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@AuthenticationPrincipal
annotation class CurrentUser