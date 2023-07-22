package com.example.meesho

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.meesho.databinding.FragmentMainScreenBinding



class MainScreen : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding
    private val model:ProductViw by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      binding.toolbar.inflateMenu(R.menu.menu)
      binding.toolbar.logo = AppCompatResources.getDrawable(requireContext(),R.drawable.tool_adobe_express)
      binding.trending.setOnClickListener {
          findNavController().navigate(R.id.action_mainScreen_to_homeScreen)
      }
        binding.toolbar.setOnMenuItemClickListener {menuItem->
            when(menuItem.itemId){
                R.id.cart -> {
                    findNavController().navigate(R.id.action_mainScreen_to_myCart)
                    true
                }
                R.id.search -> {
                    findNavController().navigate(R.id.action_mainScreen_to_searchElement)
                    true
                }
                else -> false

            }
        }
        val myList = listOf<Product>(
            Product(2,"Product 1",300.00,"This is our best good looking women top create with finest fabric inspired by italian craftman",R.drawable.top2,"",""),
            Product(1,"Product 2",200.00,"This is our best good looking women top create with finest fabric inspired by italian craftman",R.drawable.top1,"",""),
            Product(3,"Product 3",800.00,"This is our best good looking women top create with finest fabric inspired by italian craftman",R.drawable.top4,"",""),
            Product(4,"Product 4",500.00,"This is our best good looking women top create with finest fabric inspired by italian craftman",R.drawable.top3,"",""),
        )


        model.setInitialData(myList)


        val data = listOf<Product>(
          Product(2,"Product 1",300.00,"This is our best good looking women top create with finest fabric inspired by italian craftman",R.drawable.top2,"",""),
          Product(1,"Product 2",200.00,"This is our best good looking women top create with finest fabric inspired by italian craftman",R.drawable.top1,"",""),
      )
      model.popularList.value = data
      model.popularList.observe(viewLifecycleOwner){list->
          binding.showRec.adapter = PopularAdapter(list,requireContext())
          Log.d("POPULAR","${list.size}")
      }


    }
}
