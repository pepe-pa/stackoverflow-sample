package com.padamczyk.mobile.stackoverflow.common.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Error(
        @JsonProperty("error_id")
        var errorId: Int = 0,
        @JsonProperty("error_message")
        var errorMessage: String? = "",
        @JsonProperty("error_name")
        var errorName: String? = ""
)