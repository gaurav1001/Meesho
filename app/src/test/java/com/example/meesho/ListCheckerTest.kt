package com.example.meesho


import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ListCheckerTest{
  @Test
  fun `check is list element`(){
      val data = ListChecker.list.toMutableList()
      data.remove(Product(1,"product 1",40.00,"good product",1,"M","silver"))
      assertThat(data).doesNotContain(Product(1,"product 1",40.00,"good product",1,"M","silver"))
  }
}