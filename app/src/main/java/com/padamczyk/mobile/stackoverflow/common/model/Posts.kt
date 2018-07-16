package com.padamczyk.mobile.stackoverflow.common.model

data class Posts<T : StackoverflowPost>(val items: List<T>,
                                   val has_more: Boolean,
                                   val quota_max: Int,
                                   val quota_remaining: Int) {

}