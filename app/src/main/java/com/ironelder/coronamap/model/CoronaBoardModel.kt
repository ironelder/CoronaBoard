package com.ironelder.coronamap.model

data class CoronaBoardModel(
    val code: String = "",
    val case : Int = 0,
    val death : Int = 0,
    val recovered : Int = 0
)