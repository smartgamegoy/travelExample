package com.example.interviewapplication.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Data(
    val address: String,
    val category: @RawValue List<Category>,
    val distric: String,
    val elong: Double,
    val email: String,
    val facebook: String,
    val fax: String,
    val files: @RawValue List<Any>,
    val friendly: @RawValue List<Friendly>,
    val id: Int,
    val images: @RawValue List<Image>,
    val introduction: String,
    val links: @RawValue List<Link>,
    val modified: String,
    val months: String,
    val name: String,
    val name_zh: @RawValue Any,
    val nlat: Double,
    val official_site: String,
    val open_status: Int,
    val open_time: String,
    val remind: String,
    val service: @RawValue List<Service>,
    val staytime: String,
    val target: @RawValue List<Target>,
    val tel: String,
    val ticket: String,
    val url: String,
    val zipcode: String
): Parcelable