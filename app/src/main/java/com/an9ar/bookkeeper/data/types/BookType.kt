package com.an9ar.bookkeeper.data.types

enum class BookType(val title: String) {
    READ("Read"),
    CURRENTLY_READING("I'm currently reading"),
    READING_LIST("In the reading list")
}

fun String.toBookType(): BookType {
    return when (this) {
        "Read" -> BookType.READ
        "I'm currently reading" -> BookType.CURRENTLY_READING
        else -> BookType.READING_LIST
    }
}