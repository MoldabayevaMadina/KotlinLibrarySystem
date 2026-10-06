package org.example

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val library = LibraryService()

    library.loadLibraryData()

    val book1 = Book(
        1,
        "Harry Potter and the Philosopher's Stone",
        "Fantasy",
        "J. K. Rowling",
        223
    )

    val book2 = Book(
        2,
        "1984",
        "Classic",
        "George Orwell",
        328
    )

    val book3 = Book(
        3,
        "Pride and Prejudice",
        "Classic",
        "Jane Austen",
        432
    )

    val book4 = Book(
        4,
        "The Hobbit",
        "Fantasy",
        "J. R. R. Tolkien",
        310
    )

    val book5 = Book(
        5,
        "The Little Prince",
        "Classic",
        "Antoine de Saint-Exupéry",
        96
    )

    val magazine1 = Magazine(
        6,
        "National Geographic",
        "Science",
        10
    )

    val book6 = Book(
        7,
        "A Brief History of Time",
        "Science",
        "Stephen Hawking",
        256
    )

    val magazine2 = Magazine(
        8,
        "Nature",
        "Science",
        5
    )


    library.addItem(book1)
    library.addItem(book2)
    library.addItem(book3)
    library.addItem(book4)
    library.addItem(book5)
    library.addItem(magazine1)
    library.addItem(book6)
    library.addItem(magazine2)


    var running = true

    while (running) {

        println()
        println("=== LIBRARY MENU ===")
        println("1. Show all items")
        println("2. Show available items")
        println("3. Borrow item")
        println("4. Show categories")
        println("5. Show total book pages")
        println("0. Exit")

        print("Choose an option: ")

        val choice = readlnOrNull()

        when (choice) {

            "1" -> {
                println()
                println("=== ALL ITEMS ===")

                library.processItems { item ->
                    println("${item.id}. ${item.getDescription()}")
                }
            }

            "2" -> {
                println()
                println("=== AVAILABLE ITEMS ===")

                val availableItems = library.getAvailableItems()

                for (item in availableItems) {
                    println("${item.id}. ${item.title}")
                }
            }

            "3" -> {
                println()

                print("Enter your name: ")
                val name = readlnOrNull()?.trim()

                if (name.isNullOrEmpty()) {
                    println("Name cannot be empty.")
                } else {

                    println()
                    println("=== AVAILABLE ITEMS ===")

                    val availableItems = library.getAvailableItems()

                    for (item in availableItems) {
                        println("${item.id}. ${item.title}")
                    }

                    print("Enter item id: ")
                    val itemId = readlnOrNull()?.toIntOrNull()

                    if (itemId == null) {
                        println("Invalid item id.")
                    } else {

                        val user = library.getOrCreateUser(name)

                        val result = library.borrowItem(
                            itemId = itemId,
                            user = user
                        )

                        when (result) {
                            is BorrowResult.Success -> {
                                println("Success: ${result.message}")
                            }

                            is BorrowResult.Error -> {
                                println("Borrow failed: ${result.message}")
                            }
                        }
                    }
                }
            }

            "4" -> {
                println()
                println("=== CATEGORIES ===")

                val categories = library.getCategories()

                for (category in categories) {
                    println(category)
                }
            }

            "5" -> {
                println()
                println("=== TOTAL BOOK PAGES ===")

                val totalPages = library.getTotalPages()

                if (totalPages > 0) {
                    println("Total pages: $totalPages")
                } else {
                    println("There are no books in the library.")
                }
            }

            "0" -> {
                println("Goodbye!")
                running = false
            }

            else -> {
                println("Unknown option.")
            }
        }
    }
}