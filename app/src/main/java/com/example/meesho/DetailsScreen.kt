package com.example.meesho

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.meesho.databinding.FragmentDetailsScreenBinding

class DetailsScreen : Fragment() {
    private lateinit var binding:FragmentDetailsScreenBinding
    private val model:ProductViw by activityViewModels()
    private var size:String = "M"
    private var color:String = "Red"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      binding.toolbar.inflateMenu(R.menu.menu)
      binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        binding.toolbar.setOnMenuItemClickListener {menuItem->
            when(menuItem.itemId){
                R.id.cart -> {
                    findNavController().navigate(R.id.action_detailsScreen_to_myCart)
                    true
                }
                R.id.search -> {
                    true
                }
                else -> false

            }
        }
      binding.toolbar.setNavigationOnClickListener {
          requireActivity().supportFragmentManager.popBackStack()
      }


      val id = arguments?.getInt("id")
        if (id != null) {
            model.fetchDataFromList(id)
        }
        model.selectedObj.observe(viewLifecycleOwner){product->
            binding.price.text = product.price.toString()
            binding.title.text = product.title.toString()
            binding.description.text = product.description.toString()
            binding.productImg.setImageResource(product.img)
             binding.addCart.setOnClickListener{
                 model.addItemToCart(product.copy(size = size, color = color))
             }
            binding.buy.setOnClickListener {
                model.addItemToCart(product.copy(size = size, color = color))
                findNavController().navigate(R.id.action_detailsScreen_to_myCart)
            }
        }

        val radioGroup = view.findViewById<RadioGroup>(R.id.size)
        radioGroup!!.clearCheck()
        radioGroup!!.setOnCheckedChangeListener { group, checked_id ->
         when(checked_id) {
             R.id.size_1 -> {
                 size = "XS"
             }

             R.id.size_2 -> {
                 size = "X"
             }

             R.id.size_3 -> {
                 size = "M"
             }

             R.id.size_4 -> {
                 size = "L"
             }

             R.id.size_5 -> {
                 size = "XL"
             }
         }

         }

        val radioGroup2 = view.findViewById<RadioGroup>(R.id.color)
        radioGroup2.clearCheck()
        radioGroup2!!.setOnCheckedChangeListener { group, checked_id ->
            when(checked_id){
                R.id.color_1 -> {
                   color = "Red"
                }
                R.id.color_2 -> {
                    color = "Blue"
                }
                R.id.color_3 -> {
                    color = "black"
                }
            }
        }



      }

}