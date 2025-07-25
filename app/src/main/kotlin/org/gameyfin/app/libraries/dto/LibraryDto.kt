package org.gameyfin.app.libraries.dto

data class LibraryDto(
    val id: Long,
    val name: String,
    val directories: List<DirectoryMappingDto>,
    val games: List<Long>?,
    val stats: LibraryStatsDto?,
    val unmatchedPaths: List<String>? = emptyList()
)