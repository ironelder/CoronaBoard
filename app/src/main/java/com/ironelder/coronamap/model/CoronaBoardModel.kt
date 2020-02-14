package com.ironelder.coronamap.model

import com.ironelder.coronamap.BOARD_TYPE_HEADER

data class CoronaBoardModel(
    val Country: String,
    val Count : Int,
    val BoardType : Int = BOARD_TYPE_HEADER
)
