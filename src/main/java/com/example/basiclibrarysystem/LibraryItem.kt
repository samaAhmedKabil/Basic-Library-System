package com.example.basiclibrarysystem

interface LibraryItem {
    val title: String
    val isbn: String
    val publication: String
    val numberOfPages: Int
    var isAvailable: Boolean
}