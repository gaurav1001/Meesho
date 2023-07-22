package com.example.meesho

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meesho.databinding.FragmentHomeScreenBinding

class HomeScreen : Fragment(),ProductAdapter.OnProductClickListener {
    private lateinit var binding: FragmentHomeScreenBinding
    private val model:ProductViw by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.inflateMenu(R.menu.menu)
        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.toolbar.setOnMenuItemClickListener {menuItem->
           when(menuItem.itemId){
               R.id.cart -> {
                   findNavController().navigate(R.id.action_homeScreen_to_myCart)
                   true
               }
               R.id.search -> {
                   true
               }
               else -> false

           }
       }


       model.myData.observe(viewLifecycleOwner){list->
           binding.recyclerView.adapter = ProductAdapter(list,requireContext(),this)
       }

        binding.filterLowToHigh.setOnClickListener {
           model.filterLowToHigh()
        }

        binding.filterPriceHighToLow.setOnClickListener {
            model.filterHighToLow()
        }
    }

    companion object{
        const val click = "CLICK"
    }

    override fun onItemClick(product: Product) {
      val bundle = bundleOf("id" to product.id)
      findNavController().navigate(R.id.action_homeScreen_to_detailsScreen,bundle)
    }
}