package com.hivecode.data.mapper

import com.hivecode.common.mapper.DataMapper
import com.hivecode.data.model.Info
import com.hivecode.data.service.response.InfoResponse

class InfoMapper: DataMapper<InfoResponse, Info> {
    override fun from(it: InfoResponse) =
        Info(
            classes = it.classes ?: emptyList(),
            races = it.races ?: emptyList(),
            types = it.types ?: emptyList()
        )
}