package br.com.lucasalbuquerque.domain

import java.util.*

class MarvelCharacter : Data() {
    var id: Int = 0
    var name: String? = null
    var description: String? = null
    var modified: Date? = null
    var resourceURI: String? = null
    var urls: List<Url>? = null
    var thumbnail: Image? = null
    var comics: ComicList? = null
    var stories: StoryList? = null
    var events: EventList? = null
    var series: SeriesList? = null
}