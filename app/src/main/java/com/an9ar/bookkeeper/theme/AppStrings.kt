package com.an9ar.bookkeeper.theme

const val ENGLISH = "ENGLISH"
const val RUSSIAN = "RUSSIAN"

val StringsMap = hashMapOf<String, Map<Strings, String>>(
    ENGLISH to hashMapOf (
        Strings.BookTypeInProgress to "In progress"
    ),
    RUSSIAN to hashMapOf (
        Strings.BookTypeInProgress to "Читаются"
    )
)

sealed class Strings {
    object BookTypeInProgress : Strings()
}

interface AppStrings {
    val bookTypeInProgress: String
}

fun russianLocale(): AppStrings = object : AppStrings {
    override val bookTypeInProgress: String = StringsMap[RUSSIAN]?.get(Strings.BookTypeInProgress) ?: ""
}

fun englishLocale(): AppStrings = object : AppStrings {
    override val bookTypeInProgress: String = StringsMap[ENGLISH]?.get(Strings.BookTypeInProgress) ?: ""
}