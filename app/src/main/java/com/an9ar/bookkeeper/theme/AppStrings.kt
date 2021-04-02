package com.an9ar.bookkeeper.theme

const val ENGLISH = "ENGLISH"
const val RUSSIAN = "RUSSIAN"

val StringsMap = hashMapOf<String, Map<Strings, String>>(
    ENGLISH to hashMapOf (
        Strings.Settings to "Settings",
        Strings.Credits to "Credits",
        Strings.MainMenu to "Main menu",
        Strings.BookTypeRead to "Read",
        Strings.BookTypeInProgress to "In progress",
        Strings.BookTypeReadingList to "Reading list"
    ),
    RUSSIAN to hashMapOf (
        Strings.Settings to "Настройки",
        Strings.Credits to "Об авторе",
        Strings.MainMenu to "Главное меню",
        Strings.BookTypeRead to "Прочитано",
        Strings.BookTypeInProgress to "Читаются",
        Strings.BookTypeReadingList to "В списке для чтения"
    )
)

sealed class Strings {
    object Settings : Strings()
    object Credits : Strings()
    object MainMenu : Strings()
    object BookTypeRead : Strings()
    object BookTypeInProgress : Strings()
    object BookTypeReadingList : Strings()
}

interface AppStrings {
    val settings: String
    val credits: String
    val mainMenu: String
    val bookTypeRead: String
    val bookTypeInProgress: String
    val bookTypeReadingList: String
}

fun russianLocale(): AppStrings = object : AppStrings {
    override val settings: String = StringsMap[RUSSIAN]?.get(Strings.Settings) ?: ""
    override val credits: String = StringsMap[RUSSIAN]?.get(Strings.Credits) ?: ""
    override val mainMenu: String = StringsMap[RUSSIAN]?.get(Strings.MainMenu) ?: ""
    override val bookTypeRead: String = StringsMap[RUSSIAN]?.get(Strings.BookTypeRead) ?: ""
    override val bookTypeInProgress: String = StringsMap[RUSSIAN]?.get(Strings.BookTypeInProgress) ?: ""
    override val bookTypeReadingList: String = StringsMap[RUSSIAN]?.get(Strings.BookTypeReadingList) ?: ""
}

fun englishLocale(): AppStrings = object : AppStrings {
    override val settings: String = StringsMap[ENGLISH]?.get(Strings.Settings) ?: ""
    override val credits: String = StringsMap[ENGLISH]?.get(Strings.Credits) ?: ""
    override val mainMenu: String = StringsMap[ENGLISH]?.get(Strings.MainMenu) ?: ""
    override val bookTypeRead: String = StringsMap[ENGLISH]?.get(Strings.BookTypeRead) ?: ""
    override val bookTypeInProgress: String = StringsMap[ENGLISH]?.get(Strings.BookTypeInProgress) ?: ""
    override val bookTypeReadingList: String = StringsMap[ENGLISH]?.get(Strings.BookTypeReadingList) ?: ""
}