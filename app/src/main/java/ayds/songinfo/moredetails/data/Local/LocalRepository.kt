package ayds.songinfo.moredetails.data.Local

import ayds.songinfo.moredetails.fulllogic.ArtistBiography


interface LocalRepository{

    fun insertArtistIntoDB(artistBiography: ArtistBiography)

    fun getArticleFromDB(artistName: String): ArtistBiography?

}

internal class LocalRepositoryImp(): LocalRepository{

    override fun insertArtistIntoDB(artistBiography: ArtistBiography) {
        articleDatabase.ArticleDao().insertArticle(
            ArticleEntity(
                artistBiography.artistName, artistBiography.biography, artistBiography.articleUrl
            )
        )
    }

    override fun getArticleFromDB(artistName: String): ArtistBiography? {
        val artistEntity = articleDatabase.ArticleDao().getArticleByArtistName(artistName)
        return artistEntity?.let {
            ArtistBiography(artistName, artistEntity.biography, artistEntity.articleUrl)
        }
    }

}