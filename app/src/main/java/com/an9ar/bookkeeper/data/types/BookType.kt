package com.an9ar.bookkeeper.data.types

import com.an9ar.bookkeeper.R

enum class BookType(val title: String, val iconId: Int) {
    READ("Read", R.drawable.ic_books_read),
    IN_PROGRESS("In progress", R.drawable.ic_books_reading),
    READING_LIST("Reading list", R.drawable.ic_books_future_list)
}

fun String.toBookType(): BookType {
    return when (this) {
        "Read" -> BookType.READ
        "I'm currently reading" -> BookType.IN_PROGRESS
        else -> BookType.READING_LIST
    }
}