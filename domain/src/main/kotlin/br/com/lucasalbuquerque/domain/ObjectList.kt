package br.com.lucasalbuquerque.domain

open class ObjectList<T : Summary> () {
    var available: Int? = 0
    var returned: Int? = 0
    var collectionURI: String? = null
    var items: List<T>? = null
}