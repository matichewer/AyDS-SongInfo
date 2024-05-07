package ayds.songinfo.moredetails.domain
import ayds.songinfo.moredetails.domain.entities.Artist.ArtistBiography

interface moreDetailsRepository{

    fun getArtistInfoFromRepository(): ArtistBiography

}