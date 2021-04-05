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
        Strings.BookTypeReadingList to "Reading list",
        Strings.ChooseBookStatus to "Book status",
        Strings.AddNewBook to "Add new book",
        Strings.EditBookInfo to "Edit book info",
        Strings.BookTitle to "Book title",
        Strings.BookAuthor to "Book author",
        Strings.ShortDescription to "Your short description (optional)",
        Strings.Save to "Save",
    ),
    RUSSIAN to hashMapOf (
        Strings.Settings to "Настройки",
        Strings.Credits to "Об авторе",
        Strings.MainMenu to "Главное меню",
        Strings.BookTypeRead to "Прочитано",
        Strings.BookTypeInProgress to "Читаются",
        Strings.BookTypeReadingList to "В списке для чтения",
        Strings.ChooseBookStatus to "Статус книги",
        Strings.AddNewBook to "Добавить книгу",
        Strings.EditBookInfo to "Редактировать книгу",
        Strings.BookTitle to "Название",
        Strings.BookAuthor to "Автор",
        Strings.ShortDescription to "Краткое описание (опционально)",
        Strings.Save to "Сохранить",
    )
)

sealed class Strings {
    object Settings : Strings()
    object Credits : Strings()
    object MainMenu : Strings()
    object BookTypeRead : Strings()
    object BookTypeInProgress : Strings()
    object BookTypeReadingList : Strings()
    object ChooseBookStatus : Strings()
    object AddNewBook : Strings()
    object EditBookInfo : Strings()
    object BookTitle : Strings()
    object BookAuthor : Strings()
    object ShortDescription : Strings()
    object Save : Strings()
}

interface AppStrings {
    val settings: String
    val credits: String
    val mainMenu: String
    val bookTypeRead: String
    val bookTypeInProgress: String
    val bookTypeReadingList: String
    val chooseBookStatus: String
    val addNewBook: String
    val editBookInfo: String
    val bookTitle: String
    val bookAuthor: String
    val shortDescription: String
    val save: String
}

fun russianLocale(): AppStrings = object : AppStrings {
    override val settings: String = StringsMap[RUSSIAN]?.get(Strings.Settings) ?: ""
    override val credits: String = StringsMap[RUSSIAN]?.get(Strings.Credits) ?: ""
    override val mainMenu: String = StringsMap[RUSSIAN]?.get(Strings.MainMenu) ?: ""
    override val bookTypeRead: String = StringsMap[RUSSIAN]?.get(Strings.BookTypeRead) ?: ""
    override val bookTypeInProgress: String = StringsMap[RUSSIAN]?.get(Strings.BookTypeInProgress) ?: ""
    override val bookTypeReadingList: String = StringsMap[RUSSIAN]?.get(Strings.BookTypeReadingList) ?: ""
    override val chooseBookStatus: String = StringsMap[RUSSIAN]?.get(Strings.ChooseBookStatus) ?: ""
    override val addNewBook: String = StringsMap[RUSSIAN]?.get(Strings.AddNewBook) ?: ""
    override val editBookInfo: String = StringsMap[RUSSIAN]?.get(Strings.EditBookInfo) ?: ""
    override val bookTitle: String = StringsMap[RUSSIAN]?.get(Strings.BookTitle) ?: ""
    override val bookAuthor: String = StringsMap[RUSSIAN]?.get(Strings.BookAuthor) ?: ""
    override val shortDescription: String = StringsMap[RUSSIAN]?.get(Strings.ShortDescription) ?: ""
    override val save: String = StringsMap[RUSSIAN]?.get(Strings.Save) ?: ""
}

fun englishLocale(): AppStrings = object : AppStrings {
    override val settings: String = StringsMap[ENGLISH]?.get(Strings.Settings) ?: ""
    override val credits: String = StringsMap[ENGLISH]?.get(Strings.Credits) ?: ""
    override val mainMenu: String = StringsMap[ENGLISH]?.get(Strings.MainMenu) ?: ""
    override val bookTypeRead: String = StringsMap[ENGLISH]?.get(Strings.BookTypeRead) ?: ""
    override val bookTypeInProgress: String = StringsMap[ENGLISH]?.get(Strings.BookTypeInProgress) ?: ""
    override val bookTypeReadingList: String = StringsMap[ENGLISH]?.get(Strings.BookTypeReadingList) ?: ""
    override val chooseBookStatus: String = StringsMap[ENGLISH]?.get(Strings.ChooseBookStatus) ?: ""
    override val addNewBook: String = StringsMap[ENGLISH]?.get(Strings.AddNewBook) ?: ""
    override val editBookInfo: String = StringsMap[ENGLISH]?.get(Strings.EditBookInfo) ?: ""
    override val bookTitle: String = StringsMap[ENGLISH]?.get(Strings.BookTitle) ?: ""
    override val bookAuthor: String = StringsMap[ENGLISH]?.get(Strings.BookAuthor) ?: ""
    override val shortDescription: String = StringsMap[ENGLISH]?.get(Strings.ShortDescription) ?: ""
    override val save: String = StringsMap[ENGLISH]?.get(Strings.Save) ?: ""
}