package com.example.crop2cash.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crop2cash.data.model.ExhibitClass
import com.example.crop2cash.data.model.ExhibitClassItem
import com.example.crop2cash.databinding.FragmentCropCaashBinding
import com.example.crop2cash.utils.ConnectivityLiveData
import com.example.crop2cash.utils.Resource
import com.example.crop2cash.viewmodel.CropCashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CropCashFragment : Fragment() {

    private val viewModel: CropCashViewModel by viewModels()
    private  var _binding: FragmentCropCaashBinding?= null
    private val binding get() = _binding!!
    private lateinit var cropCashAdapter: CropCashAdapter
    private lateinit var recyclerView: RecyclerView
    lateinit var connectivityLiveData: ConnectivityLiveData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentCropCaashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //handling network if there is network or not
        connectivityLiveData = (activity as MainActivity).connectivityLiveData
        //_binding = FragmentCropCaashBinding.bind(view)

        observers()
        viewModel.userResponse

        //handling network state
        connectivityLiveData.observe(
            viewLifecycleOwner
        ) { hasNetwork ->
            if (hasNetwork) viewModel.fetchCar()

        }


        recyclerView = binding.recyclerViewId

        // implement swipe to refresh
        binding.competitionFragmentSwipeRefreshLayout.setOnRefreshListener {
            binding.competitionFragmentSwipeRefreshLayout.isRefreshing = false
        }
    }

    //checking for network on success
    private fun observers() {
        viewModel.userResponse.observe(viewLifecycleOwner) { product ->
            when (product) {
                is Resource.Success -> {
                    product.value?.let {
                        binding.cropProgress.visibility = View.INVISIBLE
                        setUpRecyclerView(it)
                    }
                }
                is Resource.Error -> {
                    binding.cropProgress.visibility = View.INVISIBLE

                }
                is Resource.Loading -> {
                    binding.cropProgress.visibility = View.VISIBLE
                }
            }
        }
    }


    //setting up the recyclerview with the adapterclass
    private fun setUpRecyclerView(list: ExhibitClass) { //setting the recyclerView to gridlayout
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        cropCashAdapter = CropCashAdapter(list)
        recyclerView.adapter = cropCashAdapter

    }

}
