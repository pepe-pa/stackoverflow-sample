package com.padamczyk.mobile.stackoverflow.common.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
        val reputation: Int,
        @JsonProperty("user_id")
        val userId: Int,
        @JsonProperty("user_type")
        val userType: String,
        @JsonProperty("profile_image")
        val profileImage: String?,
        @JsonProperty("display_name")
        val displayName: String,
        val link: String?
) : Parcelable
