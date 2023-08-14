package com.example.interviewapplication.utils

import com.example.interviewapplication.data.Language

object Constants {

    const val BASE_URL = "https://www.travel.taipei/open-api/"

    var language: String = "zh-tw"
    var title: String = "旅遊資訊"
    var tel: String = "電話:"
    var address: String = "地址:"

    val languageList = arrayListOf<Language>(
        Language("zh-tw", "正體中文"),
        Language("zh-cn", "简体中文"),
        Language("en", "English"),
        Language("ja", "日本語"),
        Language("ko", "한국인"),
        Language("es", "español"),
        Language("id", "Indonesia"),
        Language("th", "แบบไทย"),
        Language("vi", "Tiếng Việt"),
    )

    val appTitleList = arrayListOf<Language>(
        Language("zh-tw", "旅遊資訊"),
        Language("zh-cn", "旅游资讯"),
        Language("en", "Travel Information"),
        Language("ja", "旅行情報"),
        Language("ko", "여행 정보"),
        Language("es", "Información De Viaje"),
        Language("id", "Informasi Travel"),
        Language("th", "ข้อมูลการเดินทาง"),
        Language("vi", "thông tin du lịch"),
    )

    val telList = arrayListOf<Language>(
        Language("zh-tw", "電話:"),
        Language("zh-cn", "电话:"),
        Language("en", "Telephone:"),
        Language("ja", "電話："),
        Language("ko", "전화:"),
        Language("es", "Teléfono:"),
        Language("id", "Telepon:"),
        Language("th", "โทรศัพท์:"),
        Language("vi", "Điện thoại:"),
    )

    val addressList = arrayListOf<Language>(
        Language("zh-tw", "地址:"),
        Language("zh-cn", "地址:"),
        Language("en", "Address:"),
        Language("ja", "住所："),
        Language("ko", "주소:"),
        Language("es", "DIRECCIÓN:"),
        Language("id", "alamat:"),
        Language("th", "ที่อยู่:"),
        Language("vi", "Địa chỉ:"),
    )
}