package org.example

data class User(
    val id: Int,
    val name: String
)

sealed class BorrowResult {

    data class Success(
        val message: String
    ) : BorrowResult()

    data class Error(
        val message: String
    ) : BorrowResult()
}