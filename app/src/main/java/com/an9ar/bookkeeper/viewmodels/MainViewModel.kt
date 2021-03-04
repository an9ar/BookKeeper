package com.an9ar.bookkeeper.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.an9ar.bookkeeper.data.models.BookModel
import io.realm.Realm

class MainViewModel @ViewModelInject constructor() : ViewModel() {

    private val realmObject: Realm by lazy { Realm.getDefaultInstance() }

    val actualBookCollection = MutableLiveData<List<BookModel>>()

    fun getBooksList() {
        val newList = realmObject.where(BookModel::class.java).findAll()
        actualBookCollection.value = newList
    }

    fun addNewBook(bookData: BookModel) {
        realmObject.executeTransactionAsync { realm ->
            realm.insert(bookData)
        }
        getBooksList()
    }

}
