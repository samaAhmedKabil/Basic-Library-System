package com.example.basiclibrarysystem

fun main() {
    val library = DB()

    while (true) {
        println("\nLibrary System Menu:")
        println("1. Add a new item")
        println("2. Lend an item")
        println("3. Return an item")
        println("4. View available items")
        println("5. Search for an item")
        println("6. List borrowed items")
        println("7. Exit")
        print("Choose an option: ")

        when (readln().toInt()) {
            1 -> {
                println("\nEnter the type of item (Book, Journal, Magazine): ")
                val type = readln()
                println("Enter the title: ")
                val title = readln()
                println("Enter the ISBN: ")
                val isbn = readln()
                println("Enter the publication: ")
                val publication = readln()
                println("Enter the number of pages: ")
                val numberOfPages = readln().toInt()

                val item: LibraryItem = when (type.lowercase()) {
                    "book" -> Book(title, isbn, publication, numberOfPages)
                    "journal" -> Journal(title, isbn, publication, numberOfPages)
                    "magazine" -> Magazine(title, isbn, publication, numberOfPages)
                    else -> throw IllegalArgumentException("Invalid item type")
                }
                library.addLibraryItem(item)
                println("$type added successfully!")
            }
            2 -> {
                println("\nEnter the title of the item to lend: ")
                val title = readln()
                val items = library.searchForLibraryItem(title)
                if (items.isNotEmpty()) {
                    val item = items.first()
                    if (item.isAvailable) {
                        println("Enter user name: ")
                        val userName = readln()
                        println("Enter user ID: ")
                        val userId = readln()
                        val user = User(userName, userId)
                        library.lendLibraryItem(item, user)
                        println("Item lent successfully to $userName")
                    } else {
                        println("Item is not available")
                    }
                } else {
                    println("Item not found")
                }
            }
            3 -> {
                println("\nEnter the title of the item to return: ")
                val title = readln()
                val items = library.searchForLibraryItem(title)
                if (items.isNotEmpty()) {
                    val item = items.first()
                    if (!item.isAvailable) {
                        library.receiveLibraryItemFromBorrower(item)
                        println("Item returned successfully")
                    } else {
                        println("Item was not borrowed")
                    }
                } else {
                    println("Item not found")
                }
            }
            4 -> {
                val availableItems = library.viewAvailableItems()
                if (availableItems.isNotEmpty()) {
                    println("\nAvailable items:")
                    availableItems.forEach { println(it.title) }
                } else {
                    println("No available items")
                }
            }
            5 -> {
                println("\nEnter the title to search for: ")
                val title = readln()
                val searchResults = library.searchForLibraryItem(title)
                if (searchResults.isNotEmpty()) {
                    println("\nSearch results:")
                    searchResults.forEach { println(it.title) }
                } else {
                    println("No items found")
                }
            }
            6 -> {
                val borrowedItems = library.listBorrowedItems()
                if (borrowedItems.isNotEmpty()) {
                    println("\nBorrowed items:")
                    borrowedItems.forEach { (item, user) ->
                        println("${item.title} borrowed by ${user.name}")
                    }
                } else {
                    println("No borrowed items")
                }
            }
            7 -> {
                println("Exiting...")
                break
            }
            else -> println("Invalid option, please try again.")
        }
    }
}
