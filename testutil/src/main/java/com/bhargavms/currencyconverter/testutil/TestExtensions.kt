package com.bhargavms.currencyconverter.testutil

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers

inline infix fun <reified T: Any> T.shouldEqual(that: T) {
    assertThat(this, Matchers.equalTo(that))
}
