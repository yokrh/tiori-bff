package com.example.tiori.bff.model

import java.io.Serializable

data class PageLayout(
    var type: PageLayoutType,
    var blockIdList: List<Int>,
) : Serializable
