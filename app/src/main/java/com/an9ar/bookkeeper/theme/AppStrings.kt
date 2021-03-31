package com.an9ar.bookkeeper.theme

const val ENGLISH = "ENGLISH"
const val RUSSIAN = "RUSSIAN"

val StringsMap = hashMapOf<String, Map<Strings, String>>(
    ENGLISH to hashMapOf (
        Strings.BookTypeRead to "Read",
        Strings.BookTypeInProgress to "In progress",
        Strings.BookTypeReadingList to "Reading list"
    ),
    RUSSIAN to hashMapOf (
        Strings.BookTypeRead to "Прочитано",
        Strings.BookTypeInProgress to "Читаются",
        Strings.BookTypeReadingList to "В списке для чтения"
    )
)

sealed class Strings {
    object BookTypeRead : Strings()
    object BookTypeInProgress : Strings()
    object BookTypeReadingList : Strings()
}

interface AppStrings {
    val bookTypeRead: String
    val bookTypeInProgress: String
    val bookTypeReadingList: String
}

fun russianLocale(): AppStrings = object : AppStrings {
    override val bookTypeRead: String = StringsMap[RUSSIAN]?.get(Strings.BookTypeRead) ?: ""
    override val bookTypeInProgress: String = StringsMap[RUSSIAN]?.get(Strings.BookTypeInProgress) ?: ""
    override val bookTypeReadingList: String = StringsMap[RUSSIAN]?.get(Strings.BookTypeReadingList) ?: ""
}

fun englishLocale(): AppStrings = object : AppStrings {
    override val bookTypeRead: String = StringsMap[ENGLISH]?.get(Strings.BookTypeRead) ?: ""
    override val bookTypeInProgress: String = StringsMap[ENGLISH]?.get(Strings.BookTypeInProgress) ?: ""
    override val bookTypeReadingList: String = StringsMap[ENGLISH]?.get(Strings.BookTypeReadingList) ?: ""
}