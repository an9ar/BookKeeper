package com.an9ar.bookkeeper.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookModel(
    @PrimaryKey var id: String = "",
    var title: String = "",
    var author: String = "",
    var previewUrl: String = "",
    var comment: String = "",
    var bookType: String = ""
): RealmObject() {
    override fun toString(): String {
        return "BookModel(id='$id', title='$title', author='$author', previewUrl='$previewUrl', comment='$comment', bookType=$bookType)"
    }
}