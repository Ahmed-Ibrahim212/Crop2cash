package com.example.crop2cash.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crop2cash.R
import com.example.crop2cash.data.model.ExhibitClassItem
import com.example.crop2cash.databinding.ViewpagerItemBinding

class ViewPagerAdapter(
    private val list: ExhibitClassItem
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>( ){

    class ViewPagerHolder(var binding: ViewpagerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exhibitClassItem: ExhibitClassItem) {
            binding.apply {
                Glide.with(binding.root.context)
                    .load(exhibitClassItem.images[adapterPosition])
                    .placeholder(R.drawable.ic_baseline_people_outline_24)
                    .into(cropImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val binding = ViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return ViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bind(list)
    }

    override fun getItemCount(): Int {
        return list.images.size
    }
}