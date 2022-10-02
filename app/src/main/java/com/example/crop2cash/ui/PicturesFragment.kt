package com.example.crop2cash.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crop2cash.R
import com.example.crop2cash.data.model.ExhibitClass
import com.example.crop2cash.data.model.ExhibitClassItem
import com.example.crop2cash.databinding.FragmentCropCaashBinding
import com.example.crop2cash.databinding.FragmentPicturesBinding
import com.example.crop2cash.databinding.ViewpagerItemBinding
import com.example.crop2cash.utils.ConnectivityLiveData
import com.example.crop2cash.utils.Resource
import com.example.crop2cash.viewmodel.CropCashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PicturesFragment : Fragment() {

    private var _binding: FragmentPicturesBinding? = null
    private val binding get() = _binding!!
    private val args: PicturesFragmentArgs by navArgs()
    private lateinit var phone: ExhibitClassItem
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPicturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        phone = args.phones

        recyclerView = binding.recyclerViewIc

        setUpRecyclerView(phone)
    }

    private fun setUpRecyclerView(list: ExhibitClassItem) { //setting the recyclerView to gridlayout
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
         viewPagerAdapter = ViewPagerAdapter(list)
        recyclerView.adapter = viewPagerAdapter

    }

}