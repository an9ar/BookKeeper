package com.an9ar.bookkeeper.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.an9ar.bookkeeper.data.models.BookModel
import com.an9ar.bookkeeper.theme.AppTheme
import com.an9ar.bookkeeper.ui.*
import com.an9ar.bookkeeper.ui.BookScreenSubmitButton
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
                title = AppTheme.strings.addNewBook,
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
            layoutWeight = 0.35f,
            onImagePreviewChanged = { bookData.previewUrl = it }
        )
        BookScreenTypeDropdownMenu(
            scope = this,
            layoutWeight = 0.125f,
            onBookTypeChanged = { bookData.bookType = it }
        )
        BookScreenInputField(
            scope = this,
            layoutWeight = 0.125f,
            label = AppTheme.strings.bookTitle,
            isValidated = isValidated,
            onInputValueChanged = { bookData.title = it }
        )
        BookScreenInputField(
            scope = this,
            layoutWeight = 0.125f,
            label = AppTheme.strings.bookAuthor,
            isValidated = isValidated,
            onInputValueChanged = { bookData.author = it }
        )
        BookScreenInputField(
            scope = this,
            layoutWeight = 0.125f,
            label = AppTheme.strings.shortDescription,
            onInputValueChanged = { bookData.comment = it }
        )
        BookScreenSubmitButton(
            scope = this,
            layoutWeight = 0.15f,
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