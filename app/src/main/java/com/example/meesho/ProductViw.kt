package com.example.meesho

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class ProductViw : ViewModel() {
    // Create a LiveData with a String
    val myData: MutableLiveData<List<Product>> by lazy {
        MutableLiveData<List<Product>>()
    }

    private val _cartList: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()

    val cartList: MutableLiveData<List<Product>>
        get() = _cartList


    private val _popularList:MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    val popularList:MutableLiveData<List<Product>>
        get() = _popularList

    val selectedObj: MutableLiveData<Product> by lazy{
        MutableLiveData<Product>()
    }

    val searchObj:MutableLiveData<Product> = MutableLiveData()


    init {
        _cartList.value = emptyList() // Initialize with an empty list
        _popularList.value = emptyList()
    }

    fun setInitialData(product: List<Product>){
        myData.value =  product
    }

    fun fetchDataFromList(id: Int){
        for (item in myData.value ?: emptyList()) {
             if (item.id == id) {
                selectedObj.value = item
            }
        }
    }


    fun addItemToCart(product: Product) {
        val currentCartList = _cartList.value.orEmpty().toMutableList()
        currentCartList.add(product)
        _cartList.value = currentCartList
    }

    fun totalAmtOnCart(product: List<Product>):Double{
        var totalAmt = 0.00
        for(i in product){
            totalAmt += i.price
        }
        return totalAmt
    }

    fun removeFromCart(product: Product){
        val data = _cartList.value.orEmpty().toMutableList()
        data.remove(product)
        _cartList.value = data
    }

   fun filterLowToHigh(){
       val data = myData.value.orEmpty().toMutableList()
       data.sortBy {
           it.price
       }
       myData.value = data
   }

   fun filterHighToLow(){
       val data = myData.value.orEmpty().toMutableList()
       data.sortByDescending {
           it.price
       }
       myData.value = data
   }

   fun searchFromList(data:String){
       val findElement = myData.value?.find {product->
           product.title == data
       }
       if(findElement != null){
           searchObj.value = findElement!!
       }
   }
}