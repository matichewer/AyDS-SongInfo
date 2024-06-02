package ayds.songinfo.moredetails.data

import ayds.artist.external.lastfm.LastFmBiography
import ayds.artist.external.lastfm.LastFmService
import ayds.songinfo.moredetails.data.local.OtherInfoLocalStorage
import ayds.songinfo.moredetails.domain.Card
import ayds.songinfo.moredetails.domain.CardSource
import ayds.songinfo.moredetails.domain.OtherInfoRepository
import java.util.LinkedList

internal class OtherInfoRepositoryImpl(
    private val otherInfoLocalStorage: OtherInfoLocalStorage,
    private val lastFmService: LastFmService,
) : OtherInfoRepository {

    override fun getCard(artistName: String): LinkedList<Card> {
        val dbCard = otherInfoLocalStorage.getCard(artistName)
        val cardList: LinkedList<Card>


        if (dbCard.count()) {
            card = dbCard.apply { markItAsLocal() }
        } else {
            card = lastFmService.getArticle(artistName).toCard()
            if (card.text.isNotEmpty()) {
                otherInfoLocalStorage.insertCard(card)
            }
        }
        return cardList
    }

    private fun Card.markItAsLocal() {
        isLocallyStored = true
    }
}

private fun LastFmBiography.toCard() =
    Card(artistName, biography, articleUrl, CardSource.LAST_FM)