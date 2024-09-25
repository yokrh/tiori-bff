package com.example.tiori.bff.controller.request

import com.example.tiori.bff.model.PageLayoutType
import com.fasterxml.jackson.annotation.JsonProperty

data class CreatePageRequest(
    @JsonProperty("shioriId")
    var shioriId: Long,

    @JsonProperty("layout")
    var layout: PageLayoutContainer,
) {
    data class PageLayoutContainer(
        @JsonProperty("list")
        var pageLayoutList: List<PageLayout>
    )

    data class PageLayout(
        @JsonProperty("type")
        var type: PageLayoutType,

        @JsonProperty("blockIdList")
        var blockIdList: List<Int>,
    )
}
