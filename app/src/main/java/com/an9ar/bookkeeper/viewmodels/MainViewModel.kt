package com.an9ar.bookkeeper.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.an9ar.bookkeeper.data.models.BookModel
import io.realm.Realm

class MainViewModel @ViewModelInject constructor(
    private val realmObject: Realm
) : ViewModel() {

    //private val realmObject: Realm by lazy { Realm.getDefaultInstance() }

    val actualBookCollection = MutableLiveData<List<BookModel>>()

    fun getBooksList() {
        val newList = realmObject.where(BookModel::class.java).findAll()
        actualBookCollection.value = newList
    }

    fun getBookById(id: String): BookModel? {
        return realmObject.where(BookModel::class.java).equalTo("id", id).findFirst()
    }

    fun insertOrUpdateBook(bookData: BookModel) {
        realmObject.executeTransactionAsync { realm ->
            realm.insertOrUpdate(bookData)
        }
        getBooksList()
    }

}
