package ayds.songinfo.home.model.entities

import java.time.LocalDate
import java.time.format.DateTimeFormatter

sealed class Song {
    data class SpotifySong(
        val id: String,
        val songName: String,
        val artistName: String,
        val albumName: String,
        val releaseDate: String,
        var releaseDatePrecision: String,
        val spotifyUrl: String,
        val imageUrl: String,
        var isLocallyStored: Boolean = false
    ) : Song() {
        val parsedDate = parseDate(releaseDate, releaseDatePrecision)

        private fun parseDate(releaseDate: String, releaseDatePrecision: String): String {
            return when(releaseDatePrecision){
                "day" -> getDayFormat(releaseDate)
                "month" -> getMonthFormat(releaseDate)
                "year" -> getYearFormat(releaseDate)
                else -> releaseDate
            }
        }

        private fun getDayFormat(releaseDate: String): String {
            val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val oldDate = LocalDate.parse(releaseDate, inputFormatter)
            return oldDate.format(outputFormatter)
        }
        private fun getMonthFormat(releaseDate: String): String {
            val yearNumber = releaseDate.substringBefore('-')
            val monthNumber = releaseDate.substringAfter('-')
            val monthFormatted = monthMapping(monthNumber)
            return "$monthFormatted, $yearNumber"
        }

        private fun monthMapping(month: String): String {
            return when(month){
                "01" -> "January"
                "02" -> "February"
                "03" -> "March"
                "04" -> "April"
                "05" -> "May"
                "06" -> "June"
                "07" -> "July"
                "08" -> "August"
                "09" -> "September"
                "10" -> "October"
                "11" -> "November"
                else -> "December"
            }
        }

        private fun getYearFormat(releaseDate: String): String {
            val year = releaseDate.toIntOrNull() ?: return "Invalid date format"
            return if (isLeapYear(year)) {
                "$releaseDate (a leap year)"
            } else {
                "$releaseDate (not a leap year)"
            }
        }

        private fun isLeapYear(year: Int): Boolean {
            return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0)
        }
    }

    object EmptySong : Song()
}
