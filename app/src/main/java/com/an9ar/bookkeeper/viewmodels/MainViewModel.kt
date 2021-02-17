package com.an9ar.bookkeeper.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.an9ar.bookkeeper.data.BookModel

class MainViewModel @ViewModelInject constructor() : ViewModel() {

    //test method
    val mockBooks = listOf(
        BookModel(
            id = 0L,
            title = "Сто лет одиночества",
            author = "Габриэль Гарсиа Маркес",
            previewUrl = "",
            comment = ""
        ),
        BookModel(
            id = 1L,
            title = "Полковнику никто не пишет",
            author = "Габриэль Гарсиа Маркес",
            previewUrl = "",
            comment = ""
        ),
        BookModel(
            id = 2L,
            title = "1984",
            author = "Джордж Оруэлл",
            previewUrl = "https://img3.labirint.ru/rc/c764dc79816b3b680d5e4fbc56706ca7/220x340/books42/419735/cover.jpg?1563737926",
            comment = ""
        ),
        BookModel(
            id = 3L,
            title = "451 градус по Фаренгейту",
            author = "Рэй Брэдбери",
            previewUrl = "",
            comment = ""
        ),
        BookModel(
            id = 4L,
            title = "Сто лет одиночества",
            author = "Габриэль Гарсиа Маркес",
            previewUrl = "",
            comment = ""
        ),
        BookModel(
            id = 5L,
            title = "Полковнику никто не пишет",
            author = "Габриэль Гарсиа Маркес",
            previewUrl = "",
            comment = ""
        ),
        BookModel(
            id = 6L,
            title = "1984",
            author = "Джордж Оруэлл",
            previewUrl = "https://img3.labirint.ru/rc/c764dc79816b3b680d5e4fbc56706ca7/220x340/books42/419735/cover.jpg?1563737926",
            comment = ""
        ),
        BookModel(
            id = 7L,
            title = "451 градус по Фаренгейту",
            author = "Рэй Брэдбери",
            previewUrl = "",
            comment = ""
        )
    )

}
