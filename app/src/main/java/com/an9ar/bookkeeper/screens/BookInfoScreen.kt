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
import com.an9ar.bookkeeper.ui.*
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
                title = AppTheme.strings.editBookInfo,
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
            layoutWeight = 0.35f,
            initImage = bookData.previewUrl,
            onImagePreviewChanged = { changedImage ->
                realmObject.executeTransaction {
                    bookData.previewUrl = changedImage
                }
            }
        )
        BookScreenTypeDropdownMenu(
            scope = this,
            layoutWeight = 0.125f,
            initValue = bookData.bookType,
            onBookTypeChanged = { changedType ->
                realmObject.executeTransaction {
                    bookData.bookType = changedType
                }
            }
        )
        BookScreenInputField(
            scope = this,
            layoutWeight = 0.125f,
            initValue = bookData.title,
            label = AppTheme.strings.bookTitle,
            isValidated = isValidated,
            onInputValueChanged = { changedString ->
                realmObject.executeTransaction {
                    bookData.title = changedString
                }
            }
        )
        BookScreenInputField(
            scope = this,
            layoutWeight = 0.125f,
            initValue = bookData.author,
            label = AppTheme.strings.bookAuthor,
            isValidated = isValidated,
            onInputValueChanged = { changedString ->
                realmObject.executeTransaction {
                    bookData.author = changedString
                }
            }
        )
        BookScreenInputField(
            scope = this,
            layoutWeight = 0.125f,
            initValue = bookData.comment,
            label = AppTheme.strings.shortDescription,
            onInputValueChanged = { changedString ->
                realmObject.executeTransaction {
                    bookData.comment = changedString
                }
            }
        )
        BookScreenSubmitButton(
            scope = this,
            layoutWeight = 0.15f,
            onSubmitClick = {
                isValidated = true
                if (bookData.title.isNotEmpty() && bookData.author.isNotEmpty()) {
                    navHostController.navigateUp()
                }
            }
        )
    }
}
