package com.example.meesho

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SoundEffectConstants.CLICK
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meesho.databinding.FragmentHomeScreenBinding

class HomeScreen : Fragment(),ProductAdapter.OnProductClickListener {
    private lateinit var binding: FragmentHomeScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       val myList = listOf<Product>(
           Product(1,"Product 1",200.00),
           Product(2,"Product 2",300.00),
           Product(3,"Product 3",500.00),
           Product(4,"Product 4",800.00)
       )
       val horizontalLayout = LinearLayoutManager(
           requireContext(),LinearLayoutManager.HORIZONTAL,false
           )

        val adapter = ProductAdapter(myList,requireContext(),this)

      // val productAdapter = ProductAdapter(myList, requireContext())

       binding.recyclerView.adapter = adapter
       binding.recyclerView.layoutManager = horizontalLayout

    }

    companion object{
        const val click = "CLICK"
    }

    override fun onItemClick(product: Product) {
      findNavController().navigate(R.id.action_homeScreen_to_detailsScreen)
    }
}