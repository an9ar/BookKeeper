package com.an9ar.bookkeeper.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.an9ar.bookkeeper.data.models.BookModel
import com.an9ar.bookkeeper.theme.AppTheme
import com.an9ar.bookkeeper.viewmodels.MainViewModel
import dev.chrisbanes.accompanist.insets.LocalWindowInsets
import dev.chrisbanes.accompanist.insets.toPaddingValues


@Composable
fun BookAddScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    Scaffold(
        topBar = {
            BookAddScreenToolbar(navHostController = navHostController)
        }
    ) {
        BookAddScreenToolbarContent(
            navHostController = navHostController,
            mainViewModel = mainViewModel
        )
    }
}

@Composable
fun BookAddScreenToolbar(
    navHostController: NavHostController
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.toolbar)
            .padding(
                LocalWindowInsets.current.systemBars
                    .toPaddingValues(
                        bottom = false,
                        additionalTop = 8.dp,
                        additionalBottom = 8.dp
                    )
            )
    ) {
        val (backButton, titleBlock) = createRefs()

        IconButton(
            onClick = {
                navHostController.navigateUp()
            },
            modifier = Modifier.constrainAs(backButton) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start, 8.dp)
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back button",
                tint = AppTheme.colors.uiSurface
            )
        }
        Text(
            text = "Add new book",
            color = AppTheme.colors.text,
            style = AppTheme.typography.h6,
            modifier = Modifier.constrainAs(titleBlock) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Composable
fun BookAddScreenToolbarContent(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    var bookData = BookModel()
    Column(modifier = Modifier
        .fillMaxSize()
        .background(AppTheme.colors.background)
    ) {
        BookAddInputField(
            scope = this,
            color = Color.Red,
            onInputValueChanged = { bookData.title = it }
        )
        BookAddInputField(
            scope = this,
            color = Color.Blue,
            onInputValueChanged = { bookData.author = it }
        )
        BookAddInputField(
            scope = this,
            color = Color.Green,
            onInputValueChanged = { bookData.comment = it }
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BookAddInputField(
    scope: ColumnScope,
    color: Color,
    onInputValueChanged: (String) -> Unit
) {
    scope.run {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.15f)
                .background(color)
        ) {
            val keyboardController = LocalSoftwareKeyboardController.current
            var inputValue by rememberSaveable { mutableStateOf("") }
            BasicTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                    onInputValueChanged(it)
                },
                singleLine = true,
                textStyle = AppTheme.typography.body1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hideSoftwareKeyboard()
                    }
                )
            )
        }
    }
}