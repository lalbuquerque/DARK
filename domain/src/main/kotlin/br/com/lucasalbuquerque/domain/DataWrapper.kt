package br.com.lucasalbuquerque.domain

class DataWrapper<T : Data> {
    var code: Int = 0
    var status: String? = null
    var copyright: String? = null
    var attributionText: String? = null
    var attributionHTML: String? = null
    var data: DataContainer<T>? = null
    var etag: String? = null
}