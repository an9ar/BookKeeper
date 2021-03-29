package com.an9ar.bookkeeper.theme

const val ENGLISH = "ENGLISH"
const val RUSSIAN = "RUSSIAN"

object StringsCollection {
    val booktype_in_progress_ru = "Читаются"
    val booktype_in_progress_en = "In progress"
}

interface AppStrings {
    val BookTypeInProgress: String
}

fun russianLocale(): AppStrings = object : AppStrings {
    override val BookTypeInProgress: String = StringsCollection.booktype_in_progress_ru
}

fun englishLocale(): AppStrings = object : AppStrings {
    override val BookTypeInProgress: String = StringsCollection.booktype_in_progress_en
}