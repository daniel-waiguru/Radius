package com.danielwaiguru.radius.testing.base_configuration

import com.danielwaiguru.radius.testing.MainCoroutineRule
import org.junit.Rule

abstract class BaseViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

}