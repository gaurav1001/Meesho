package com.example.meesho

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.meesho.databinding.FragmentSearchElementBinding

class SearchElement : Fragment(){
    private lateinit var binding:FragmentSearchElementBinding
    private val model:ProductViw by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchElementBinding.inflate(inflater, container, false)
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
                else -> false

            }
        }

        binding.search.setOnClickListener {
            val data = binding.input.text.toString()
            model.searchFromList(data)
            model.searchObj.observe(viewLifecycleOwner){product->
              if (product != null){
                  binding.result.text = product.title
              }
            }
        }


    }
}