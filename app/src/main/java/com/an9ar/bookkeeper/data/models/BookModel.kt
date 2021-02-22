package com.an9ar.bookkeeper.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookModel(
    @PrimaryKey var id: Long = 0L,
    var title: String = "",
    var author: String = "",
    var previewUrl: String = "",
    var comment: String = ""
): RealmObject()