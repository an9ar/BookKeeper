package com.an9ar.bookkeeper.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.an9ar.bookkeeper.R
import com.an9ar.bookkeeper.data.models.BookModel
import com.an9ar.bookkeeper.log
import com.an9ar.bookkeeper.theme.AppTheme
import com.an9ar.bookkeeper.viewmodels.MainViewModel
import dev.chrisbanes.accompanist.glide.GlideImage
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
        BookAddScreenContent(
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
        var titleIsEmptyError by remember { mutableStateOf(false) }
        var authorIsEmptyError by remember { mutableStateOf(false) }
        BookAddImage(
            scope = this,
            onImagePreviewChanged = { bookData.previewUrl = it }
        )
        BookAddInputField(
            scope = this,
            label = "Book title",
            isEmpty = titleIsEmptyError,
            onInputValueChanged = { bookData.title = it }
        )
        BookAddInputField(
            scope = this,
            label = "Book author",
            isEmpty = authorIsEmptyError,
            onInputValueChanged = { bookData.author = it }
        )
        BookAddInputField(
            scope = this,
            label = "Your short description (optional)",
            onInputValueChanged = { bookData.comment = it }
        )
        BookAddSubmitButton(
            scope = this,
            onSubmitClick = {
                log("book - $bookData")
                if (bookData.title.isEmpty() || bookData.author.isEmpty()) {
                    titleIsEmptyError = bookData.title.isEmpty()
                    authorIsEmptyError = bookData.author.isEmpty()
                }
                else {

                }
            }
        )
    }
}

@Composable
fun BookAddImage(
    scope: ColumnScope,
    onImagePreviewChanged: (String) -> Unit
) {
    scope.run {
        BoxWithConstraints(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.35f)
        ) {
            val screenWidth = with(LocalDensity.current) { constraints.maxWidth.toDp() }
            val screenHeight = with(LocalDensity.current) { constraints.maxHeight.toDp() }

            GlideImage(
                data = "",
                contentDescription = "Author",
                contentScale = ContentScale.Crop,
                loading = {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
                error = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_book),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(AppTheme.colors.uiSurface),
                            modifier = Modifier
                                .fillMaxSize()
                                .background(AppTheme.colors.uiSurfaceInverted)
                                .padding(48.dp)
                        )
                    }
                },
                modifier = Modifier
                    .height(screenHeight)
                    .width(screenHeight)
                    .padding(16.dp)
                    .clip(CircleShape)
                    .clickable {  }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BookAddInputField(
    scope: ColumnScope,
    label: String,
    isEmpty: Boolean = false,
    onInputValueChanged: (String) -> Unit
) {
    scope.run {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .weight(0.15f)
        ) {
            val keyboardController = LocalSoftwareKeyboardController.current
            var inputValue by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                    onInputValueChanged(it)
                },
                isError = isEmpty,
                singleLine = true,
                textStyle = AppTheme.typography.inputFieldValue,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hideSoftwareKeyboard()
                    }
                ),
                label = {
                    Text(
                        text = if (isEmpty) "$label (Should not be empty)" else label,
                        color = if (isEmpty) AppTheme.colors.error else AppTheme.colors.text,
                        textAlign = TextAlign.Start,
                        style = AppTheme.typography.inputFieldTitle,
                    )
                },
                trailingIcon = {
                    if (inputValue.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                inputValue = ""
                                onInputValueChanged(inputValue)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear button",
                                tint = AppTheme.colors.textSecondary
                            )
                        }
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = AppTheme.colors.text,
                    focusedBorderColor = AppTheme.colors.text,
                    unfocusedBorderColor = AppTheme.colors.textSecondary,
                    cursorColor = AppTheme.colors.text,
                    errorBorderColor = AppTheme.colors.error,
                    errorLabelColor = AppTheme.colors.error,
                    unfocusedLabelColor = AppTheme.colors.textSecondary,
                    focusedLabelColor = AppTheme.colors.text
                ),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun BookAddSubmitButton(
    scope: ColumnScope,
    onSubmitClick: () -> Unit
) {
    scope.run {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
        ) {
            Button(
                onClick = { onSubmitClick() },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = AppTheme.colors.uiSurface
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    text = "Save",
                    color = AppTheme.colors.uiSurfaceInverted,
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.button,
                    modifier = Modifier.padding(16.dp)
                )
            }

        }
    }
}