package com.example.meesho

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.meesho.databinding.FragmentDetailsScreenBinding

class DetailsScreen : Fragment() {
    private lateinit var binding:FragmentDetailsScreenBinding
    private val model:ProductViw by activityViewModels()

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
      val id = arguments?.getInt("id")
        if (id != null) {
            model.fetchDataFromList(id)
        }
        model.selectedObj.observe(viewLifecycleOwner){
            binding.price.text = it.price.toString()
            binding.title.text = it.title.toString()
            binding.description.text = it.description.toString()
            binding.productImg.setImageResource(it.img)

        }
    }
}