package ayds.songinfo.moredetails.data.Local

import ayds.songinfo.moredetails.domain.entities.Artist.ArtistBiography


interface LocalRepository{

    fun insertArtistIntoDB(artistBiography: ArtistBiography)

    fun getArticleFromDB(artistName: String): ArtistBiography?

}

internal class LocalRepositoryImp(val articleDatabase: ArticleDatabase): LocalRepository{

    private val articleDao = articleDatabase.ArticleDao()

    override fun insertArtistIntoDB(artistBiography: ArtistBiography) {
        articleDao.insertArticle(
            ArticleEntity(
                artistBiography.artistName, artistBiography.biography, artistBiography.articleUrl
            )
        )
    }

    override fun getArticleFromDB(artistName: String): ArtistBiography? {
        val artistEntity = articleDao.getArticleByArtistName(artistName)
        return artistEntity?.let {
            ArtistBiography(artistName, artistEntity.biography, artistEntity.articleUrl)
        }
    }

}