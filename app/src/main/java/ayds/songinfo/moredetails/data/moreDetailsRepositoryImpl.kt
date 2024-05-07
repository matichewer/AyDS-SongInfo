package ayds.songinfo.moredetails.data

import ayds.songinfo.moredetails.data.External.ExternalRepository
import ayds.songinfo.moredetails.data.Local.LocalRepository
import ayds.songinfo.moredetails.domain.entities.Artist.ArtistBiography
import android.content.Intent
import ayds.songinfo.moredetails.domain.moreDetailsRepository


class moreDetailsRepositoryImpl(
    private val ArtistBiographyLocal: ExternalRepository,
    private val ArtistBiographyExternal: LocalRepository
): moreDetailsRepository {


    private fun getArtistName() =
        intent.getStringExtra(OtherInfoWindow.ARTIST_NAME_EXTRA) ?: throw Exception("Missing artist name")

    override private fun getArtistInfoFromRepository(): ArtistBiography {
        val artistName = getArtistName()

        val dbArticle = getArticleFromDB(artistName)

        val artistBiography: ArtistBiography

        if (dbArticle != null) {
            artistBiography = dbArticle.markItAsLocal()
        } else {
            artistBiography = getArticleFromService(artistName)
            if (artistBiography.biography.isNotEmpty()) {
                insertArtistIntoDB(artistBiography)
            }
        }
        return artistBiography
    }
}

