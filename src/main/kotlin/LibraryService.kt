package org.example

import kotlinx.coroutines.delay

class LibraryService {

    private val items: MutableList<LibraryItem> = mutableListOf()

    private val users: MutableMap<Int, User> = mutableMapOf()

    fun addItem(item: LibraryItem) {
        items.add(item)
    }

    fun addUser(user: User) {
        users[user.id] = user
    }

    fun getAllItems(): List<LibraryItem> {
        return items
    }

    fun getAvailableItems(): List<LibraryItem> {
        return items.filter { item ->
            item.isAvailable
        }
    }

    fun getItemTitles(): List<String> {
        return items.map { item ->
            item.title
        }
    }

    fun getCategories(): Set<String> {
        return items.map { item ->
            item.category
        }.toSet()
    }

    fun getTotalPages(): Int {
        val books = items.filterIsInstance<Book>()

        if (books.isEmpty()) {
            return 0
        }

        return books
            .map { book ->
                book.pages
            }
            .reduce { total, pages ->
                total + pages
            }
    }

    fun borrowItem(itemId: Int, userId: Int): BorrowResult {
        val item = items.find { item ->
            item.id == itemId
        }

        val user = users[userId]

        if (item == null) {
            return BorrowResult.Error("Item with id $itemId was not found.")
        }

        if (user == null) {
            return BorrowResult.Error("User with id $userId was not found.")
        }

        if (!item.isAvailable) {
            return BorrowResult.Error("${item.title} is already borrowed.")
        }

        item.borrow()

        return BorrowResult.Success(
            "${item.title} was borrowed by ${user.name}."
        )
    }

    fun getOrCreateUser(name: String): User {
        val existingUser = users.values.find { user ->
            user.name.equals(name, ignoreCase = true)
        }

        if (existingUser != null) {
            return existingUser
        }

        val newId = if (users.isEmpty()) {
            1
        } else {
            users.keys.max() + 1
        }

        val newUser = User(newId, name)
        users[newId] = newUser

        return newUser
    }

    fun processItems(action: (LibraryItem) -> Unit) {
        for (item in items) {
            action(item)
        }
    }

    suspend fun loadLibraryData() {
        println("Loading library data...")

        delay(1000)

        println("Library data loaded!")
    }
}