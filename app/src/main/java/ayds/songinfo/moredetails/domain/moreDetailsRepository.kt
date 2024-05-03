package ayds.songinfo.moredetails.domain
import ayds.songinfo.moredetails.domain.entities.Artist

interface ArtistRepository{

    fun getArtistInfoFromRepository(): Artist

}