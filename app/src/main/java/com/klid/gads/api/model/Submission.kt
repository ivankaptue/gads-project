package com.klid.gads.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author Ivan Kaptue (ivanokaptue@gmail.com)
 */
@JsonClass(generateAdapter = true)
data class Submission(
    @Json(name = "entry.1824927963")val email: String?,
    @Json(name = "entry.1877115667") val firstname: String?,
    @Json(name = "entry.2006916086")val lastname: String?,
    @Json(name = "entry.284483984") val githubLink: String?
)