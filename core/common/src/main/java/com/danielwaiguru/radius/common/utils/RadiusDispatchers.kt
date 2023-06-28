package com.danielwaiguru.radius.common.utils

import javax.inject.Qualifier

@Qualifier
@Retention
annotation class Dispatcher(val radiusDispatchers: RadiusDispatchers)
enum class RadiusDispatchers {
    IO, MAIN, DEFAULT, UNCONFINED
}