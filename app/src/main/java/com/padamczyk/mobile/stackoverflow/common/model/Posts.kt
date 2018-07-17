package com.padamczyk.mobile.stackoverflow.common.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Posts<T : StackoverflowPost>(val items: List<T>,
                                        @JsonProperty("has_more") val hasMore: Boolean,
                                        @JsonProperty("quota_max") val quotaMax: Int,
                                        @JsonProperty("quota_remaining") val quotaRemaining: Int)