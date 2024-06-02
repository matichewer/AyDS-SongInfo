package ayds.songinfo.moredetails.data.broker

import java.util.LinkedList
import ayds.songinfo.moredetails.domain.Card

interface CardBroker {
    fun getInfoFromServices(artistName: String): LinkedList<Card>
}

internal class CardBrokerImp(val proxyList: LinkedList<OtherInfoProxy>): CardBroker {

    override fun getInfoFromServices(artistName: String): LinkedList<Card> {
        var cardAux: Card?
        val articles = LinkedList<Card>()

        for (proxy in proxyList) {
            cardAux = proxy.getCardFromExternalService(artistName)
            cardAux?.let{
                articles.addLast(cardAux)
            }
        }
    }
}