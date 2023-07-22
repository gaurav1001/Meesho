package com.example.meesho

object ListChecker {
    val list:List<Product> = listOf(
        Product(1,"product 1",70.00,"avg product",2,"XL","gold"),
        Product(2,"product 2",40.00,"good product",1,"M","silver"),
        Product(3,"product 3",30.00,"good product",1,"M","silver")
    )

    fun filterLowToHigh():MutableList<Product>{
        val data = list.orEmpty().toMutableList()
       data.sortBy {
            it.price
        }
        return data
    }
}