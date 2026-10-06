# Library Management System

A small Kotlin/JVM console application that simulates a library.

The program allows users to:
- view all library items
- view available items
- borrow a book or magazine by ID
- view categories
- calculate the total number of pages in books

## How to Run

Open the project in IntelliJ IDEA, open `Main.kt`, and run the `main()` function.

## Main Requirements

The project demonstrates:

- variables, data types, conditions, and loops (`val`, `var`, `Int`, `String`, `Boolean`, `if`, `when`, `for`, `while`)
- `List`, `Set`, and `Map` (`MutableList<LibraryItem>`, `Set<String>`, `MutableMap<Int, User>`)
- collection operations: `map`, `filter`, and `reduce` (`getCategories()`, `getAvailableItems()`, `getTotalPages()`)
- functions, higher-order functions, and lambdas (`borrowItem()`, `processItems(action: (LibraryItem) -> Unit)`, `{ item -> ... }`)
- classes and objects (`Book`, `Magazine`, `LibraryService`, `book1`, `magazine1`, `library`)
- inheritance (`Book` and `Magazine` inherit from `LibraryItem`)
- interfaces and polymorphism (`Borrowable`, `getDescription()` overridden in `Book` and `Magazine`)
- `data class` (`User`)
- `sealed class` (`BorrowResult` with `Success` and `Error`)
- a `suspend` function and a simple coroutine (`suspend fun loadLibraryData()`, `delay(1000)`, `runBlocking`)

## Project Structure

- `Main.kt` — program entry point and console menu
- `LibraryItem.kt` — interface, abstract class, books and magazines
- `LibraryService.kt` — main library logic and collections
- `Models.kt` — `User` data class and `BorrowResult` sealed class