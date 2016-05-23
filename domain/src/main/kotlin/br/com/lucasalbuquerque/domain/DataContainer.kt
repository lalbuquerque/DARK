package br.com.lucasalbuquerque.domain

class DataContainer<T : Data> {
    var offset: Int = 0
    var limit: Int = 0
    var total: Int = 0
    var count: Int = 0
    var results: List<T>? = null
}