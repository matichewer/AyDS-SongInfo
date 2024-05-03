package ayds.songinfo.moredetails.domain.entities

import ayds.songinfo.moredetails.fulllogic.ArtistBiography

sealed class Artist {

    data class ArtistBiography(
        val artistName: String,
        val biography: String,
        val articleUrl: String
    ): Artist()


    private fun markItAsLocal() = copy(biography = "[*]$biography")
}