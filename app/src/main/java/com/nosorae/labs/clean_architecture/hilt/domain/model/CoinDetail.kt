package com.nosorae.labs.clean_architecture.hilt.domain.model

import com.nosorae.labs.clean_architecture.hilt.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>
)
