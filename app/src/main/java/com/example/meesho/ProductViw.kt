package com.example.meesho

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductViw : ViewModel() {
    // Create a LiveData with a String
    val myData: MutableLiveData<List<Product>> by lazy {
        MutableLiveData<List<Product>>()
    }

    val selectedObj: MutableLiveData<Product> by lazy{
        MutableLiveData<Product>()
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

}