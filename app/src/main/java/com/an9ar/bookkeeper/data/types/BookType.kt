package com.an9ar.bookkeeper.data.types

enum class BookType(val title: String) {
    READ("Read"),
    IN_PROGRESS("In progress"),
    READING_LIST("Reading list")
}

fun String.toBookType(): BookType {
    return when (this) {
        "Read" -> BookType.READ
        "I'm currently reading" -> BookType.IN_PROGRESS
        else -> BookType.READING_LIST
    }
}