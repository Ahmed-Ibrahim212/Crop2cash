package com.example.crop2cash.ui

import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crop2cash.R
import com.example.crop2cash.data.model.ExhibitClassItem
import com.example.crop2cash.databinding.ListRecyclerItemBinding

class CropCashAdapter(private val list: List<ExhibitClassItem>): RecyclerView.Adapter<CropCashAdapter.CropCashViewHolder>() {

    class CropCashViewHolder(var binding: ListRecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(exhibitClassItem: ExhibitClassItem) {
            binding.apply {
                cropName.text = exhibitClassItem.title
                Glide.with(binding.root.context)
                    .load(exhibitClassItem.images[2])
                    .placeholder(R.drawable.ic_baseline_people_outline_24)
                    .into(cropImage)
                cropImage.setOnClickListener {
                    exhibitClassItem.let {
                        val navDirection = CropCashFragmentDirections.actionCropCashFragmentToPicturesFragment(it)
                        cropImage.findNavController().navigate(navDirection)
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CropCashViewHolder {
        val binding = ListRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return  CropCashViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CropCashViewHolder, position: Int) {
         holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
