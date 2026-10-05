package org.example

interface Borrowable {
    fun borrow()
    fun returnItem()
}

abstract class LibraryItem(
    val id: Int,
    val title: String,
    val category: String
) : Borrowable {

    var isAvailable: Boolean = true

    override fun borrow() {
        isAvailable = false
    }

    override fun returnItem() {
        isAvailable = true
    }

    abstract fun getDescription(): String
}

class Book(
    id: Int,
    title: String,
    category: String,
    val author: String,
    val pages: Int
) : LibraryItem(id, title, category) {

    override fun getDescription(): String {
        return "Book: $title by $author, pages: $pages"
    }
}

class Magazine(
    id: Int,
    title: String,
    category: String,
    val issueNumber: Int
) : LibraryItem(id, title, category) {

    override fun getDescription(): String {
        return "Magazine: $title, issue #$issueNumber"
    }
}