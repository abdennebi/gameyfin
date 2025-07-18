package org.gameyfin.plugins.metadata.steamgriddb.dto

import kotlinx.serialization.Serializable

@Serializable
data class SteamGridDbGridResult(
    val success: Boolean,
    val data: List<SteamGridDbGrid>?
)

@Serializable
data class SteamGridDbGrid(
    val id: Int,
    val url: String
)