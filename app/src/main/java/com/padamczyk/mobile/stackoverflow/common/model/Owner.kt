package com.padamczyk.mobile.stackoverflow.common.model

data class Owner(
        val reputation: Int,
        val user_id: Int,
        val user_type: String,
        val profile_image: String?,
        val display_name: String,
        val link: String?
)