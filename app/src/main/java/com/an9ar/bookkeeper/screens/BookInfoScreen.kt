package com.an9ar.bookkeeper.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.an9ar.bookkeeper.data.models.BookModel
import com.an9ar.bookkeeper.theme.AppTheme
import com.an9ar.bookkeeper.ui.BookScreenImage
import com.an9ar.bookkeeper.ui.BookScreenInputField
import com.an9ar.bookkeeper.ui.BookScreenSubmitButton
import com.an9ar.bookkeeper.ui.BookScreenToolbar
import com.an9ar.bookkeeper.viewmodels.MainViewModel
import io.realm.Realm

@Composable
fun BookInfoScreen(
    bookId: String,
    realmObject: Realm,
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    val targetBook = mainViewModel.getBookById(id = bookId)
    Scaffold(
        topBar = {
            BookScreenToolbar(
                title = "Edit book info",
                navHostController = navHostController
            )
        }
    ) {
        targetBook?.let {
            BookInfoScreenContent(
                realmObject = realmObject,
                navHostController = navHostController,
                mainViewModel = mainViewModel,
                bookModel = it
            )
        }
    }
}

@Composable
fun BookInfoScreenContent(
    realmObject: Realm,
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    bookModel: BookModel
) {
    val bookData by remember { mutableStateOf(bookModel) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
    ) {
        var isValidated by remember { mutableStateOf(false) }
        BookScreenImage(
            scope = this,
            initImage = bookData.previewUrl,
            onImagePreviewChanged = { changedImage ->
                realmObject.executeTransaction {
                    bookData.previewUrl = changedImage
                }
            }
        )
        BookScreenInputField(
            scope = this,
            initValue = bookData.title,
            label = "Book title",
            isValidated = isValidated,
            onInputValueChanged = { changedString ->
                realmObject.executeTransaction {
                    bookData.title = changedString
                }
            }
        )
        BookScreenInputField(
            scope = this,
            initValue = bookData.author,
            label = "Book author",
            isValidated = isValidated,
            onInputValueChanged = { changedString ->
                realmObject.executeTransaction {
                    bookData.author = changedString
                }
            }
        )
        BookScreenInputField(
            scope = this,
            initValue = bookData.comment,
            label = "Your short description (optional)",
            onInputValueChanged = { changedString ->
                realmObject.executeTransaction {
                    bookData.comment = changedString
                }
            }
        )
        BookScreenSubmitButton(
            scope = this,
            onSubmitClick = {
                isValidated = true
                if (bookData.title.isNotEmpty() && bookData.author.isNotEmpty()) {
                    navHostController.navigateUp()
                }
            }
        )
    }
}
