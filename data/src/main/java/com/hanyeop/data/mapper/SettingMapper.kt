package com.hanyeop.data.mapper

import com.hanyeop.data.model.setting.DataInquiry
import com.hanyeop.domain.model.setting.DomainInquiry

// Domain -> Data
fun mapperToDataInquiry(inquiry: DomainInquiry): DataInquiry {
    return DataInquiry(
        id = inquiry.id,
        content = inquiry.content,
        time = inquiry.time
    )
}