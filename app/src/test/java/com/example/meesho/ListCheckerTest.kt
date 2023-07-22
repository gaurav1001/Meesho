package com.example.meesho


import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class ListCheckerTest{
    lateinit var data:MutableList<Product>
    @Before
    fun initialize(){
         data = ListChecker.list.toMutableList()
    }

  @Test
  fun `check is list element`(){
      data.remove(Product(1,"product 1",40.00,"good product",1,"M","silver"))
      assertThat(data).doesNotContain(Product(1,"product 2",60.00,"good product",1,"M","silver"))
  }

   @Test
   fun `check is first element in list is lowest or false`(){
     val fetch = ListChecker.filterLowToHigh()
     val firstElement = fetch.first()
     assertThat(firstElement.price).isEqualTo(30.00)
   }

   @Test
   fun `check is search show or false`(){
     val findElement = data.find {product ->
         product.title == "product 2"
     }
     assertThat(findElement?.title).isEqualTo("product 2")
   }
}