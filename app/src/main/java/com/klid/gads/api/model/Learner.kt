package com.klid.gads.api.model

import com.squareup.moshi.JsonClass

/**
 * @author Ivan Kaptue (ivanokaptue@gmail.com)
 */
@JsonClass(generateAdapter = true)
data class Learner(
    val name: String?,
    val hours: Int?,
    val country: String?,
    val badgeUrl: String?
)