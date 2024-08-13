package com.example.basiclibrarysystem

class Journal(override val title: String, override val isbn: String, override val publication: String, override val numberOfPages: Int) : LibraryItem {
    override var isAvailable: Boolean = true
}