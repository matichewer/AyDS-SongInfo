package ayds.songinfo.moredetails.data

import ayds.songinfo.moredetails.data.External.ExternalRepository
import ayds.songinfo.moredetails.data.Local.LocalRepository
import ayds.songinfo.moredetails.fulllogic.ArtistBiography


class moreDetailsRepositoryImpl(
    private val ArtistBiographyLocal: ExternalRepository,
    private val ArtistBiographyExternal: LocalRepository
): moreDetailsRepository {


    private fun getArtistInfoFromRepository(): ArtistBiography {
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

