package com.example.meesho

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.meesho.databinding.FragmentMyCartBinding


class MyCart : Fragment() {
    private lateinit var binding: FragmentMyCartBinding
    private val model: ProductViw by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      binding.toolbar.inflateMenu(R.menu.menu)
      binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
      model.cartList.observe(viewLifecycleOwner){list->
         val cartAdapter = CartAdapter(list,requireContext())
         binding.productList.adapter = cartAdapter
         cartAdapter.setOnClickListener(object :CartAdapter.OnItemClickListener{
             override fun onClick(product: Product) {
                 model.removeFromCart(product)
             }

         })
         binding.totalAmt.text = model.totalAmtOnCart(list).toString()
         binding.subtotalAmt.text = model.totalAmtOnCart(list).toString()

      }



      binding.toolbar.setNavigationOnClickListener {
          requireActivity().supportFragmentManager.popBackStack()
      }
    }
}