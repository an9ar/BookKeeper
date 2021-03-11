package com.an9ar.bookkeeper.data.models

import com.an9ar.bookkeeper.data.types.BookType
import com.an9ar.bookkeeper.data.types.toBookType

data class BookEntity(
    val id: String,
    val title: String = "",
    val author: String = "",
    val previewUrl: String = "",
    val comment: String = "",
    val bookType: BookType = BookType.IN_PROGRESS
)

fun BookModel.toBookEntity(): BookEntity =
    BookEntity(
        id = this.id,
        title = this.title,
        author = this.author,
        previewUrl = this.previewUrl,
        comment = this.comment,
        bookType = this.bookType.toBookType()
    )
