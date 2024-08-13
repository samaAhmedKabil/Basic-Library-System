package com.example.basiclibrarysystem

class DB {
    private val items = mutableListOf<LibraryItem>()
    private val borrowedItems = mutableMapOf<LibraryItem, User>()

    fun addLibraryItem(item: LibraryItem) {
        items.add(item)
    }

    fun lendLibraryItem(item: LibraryItem, user: User): Boolean {
        return if (item.isAvailable) {
            item.isAvailable = false
            borrowedItems[item] = user
            true
        } else {
            false
        }
    }

    fun receiveLibraryItemFromBorrower(item: LibraryItem): Boolean {
        return if (borrowedItems.containsKey(item)) {
            item.isAvailable = true
            borrowedItems.remove(item)
            true
        } else {
            false
        }
    }

    fun searchForLibraryItem(title: String): List<LibraryItem> {
        return items.filter { it.title.contains(title, ignoreCase = true) }
    }

    fun viewAvailableItems(): List<LibraryItem> {
        return items.filter { it.isAvailable }
    }

    fun listBorrowedItems(): Map<LibraryItem, User> {
        return borrowedItems
    }
}
