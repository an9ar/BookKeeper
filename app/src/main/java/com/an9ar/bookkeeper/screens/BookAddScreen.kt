package com.an9ar.bookkeeper.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import java.util.*


@Composable
fun BookAddScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    Scaffold(
        topBar = {
            BookScreenToolbar(
                title = "Add new book",
                navHostController = navHostController
            )
        }
    ) {
        BookAddScreenContent(
            navHostController = navHostController,
            mainViewModel = mainViewModel
        )
    }
}

@Composable
fun BookAddScreenContent(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    val bookData by remember { mutableStateOf(BookModel()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
    ) {
        var isValidated by remember { mutableStateOf(false) }
        BookScreenImage(
            scope = this,
            onImagePreviewChanged = { bookData.previewUrl = it }
        )
        BookScreenInputField(
            scope = this,
            label = "Book title",
            isValidated = isValidated,
            onInputValueChanged = { bookData.title = it }
        )
        BookScreenInputField(
            scope = this,
            label = "Book author",
            isValidated = isValidated,
            onInputValueChanged = { bookData.author = it }
        )
        BookScreenInputField(
            scope = this,
            label = "Your short description (optional)",
            onInputValueChanged = { bookData.comment = it }
        )
        BookScreenSubmitButton(
            scope = this,
            onSubmitClick = {
                isValidated = true
                if (bookData.title.isNotEmpty() && bookData.author.isNotEmpty()) {
                    bookData.id =
                        Calendar.getInstance().timeInMillis.toString() + " " + UUID.randomUUID()
                            .toString()
                    mainViewModel.insertOrUpdateBook(bookData = bookData)
                    navHostController.navigateUp()
                }
            }
        )
    }
}