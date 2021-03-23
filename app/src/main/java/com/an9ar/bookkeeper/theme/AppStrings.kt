package com.an9ar.bookkeeper.theme

import com.an9ar.bookkeeper.utils.Translatable
import com.an9ar.bookkeeper.utils.registerSupportedLocales
import java.util.*

val ENGLISH = Locale("en")
val RUSSIAN = Locale("ru")

val supportedLocalesNow = registerSupportedLocales(ENGLISH, RUSSIAN)

val booktype_in_progress = Translatable("in_progress", "In progress") {
    hashMapOf(
        ENGLISH to "In progress",
        RUSSIAN to "Читаются"
    )
}
val booktype_reading_list = Translatable("reading_list", "Reading list") {
    hashMapOf(
        ENGLISH to "Reading list",
        RUSSIAN to "В списке для чтения"
    )
}
val booktype_read = Translatable("read", "Read") {
    hashMapOf(
        ENGLISH to "Read",
        RUSSIAN to "Прочитано"
    )
}