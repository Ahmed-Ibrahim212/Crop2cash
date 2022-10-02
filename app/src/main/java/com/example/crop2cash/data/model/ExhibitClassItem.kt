package com.example.crop2cash.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExhibitClassItem(
    val images: List<String>,
    val title: String
): Parcelable