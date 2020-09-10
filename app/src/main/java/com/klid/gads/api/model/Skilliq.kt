package com.klid.gads.api.model

import com.squareup.moshi.JsonClass

/**
 * @author Ivan Kaptue (ivanokaptue@gmail.com)
 */
@JsonClass(generateAdapter = true)
data class Skilliq(
    val name: String?,
    val score: Int?,
    val country: String?,
    val badgeUrl: String?
)