package com.an9ar.bookkeeper.screens

import androidx.compose.animation.animateContentSize
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.an9ar.bookkeeper.R
import com.an9ar.bookkeeper.data.models.BookModel
import com.an9ar.bookkeeper.theme.AppTheme
import com.an9ar.bookkeeper.viewmodels.MainViewModel
import dev.chrisbanes.accompanist.glide.GlideImage
import dev.chrisbanes.accompanist.insets.LocalWindowInsets
import dev.chrisbanes.accompanist.insets.toPaddingValues
import java.util.*


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
        var isValidated by remember { mutableStateOf(false) }
        BookAddImage(
            scope = this,
            onImagePreviewChanged = { bookData.previewUrl = it }
        )
        BookAddInputField(
            scope = this,
            label = "Book title",
            isValidated = isValidated,
            onInputValueChanged = { bookData.title = it }
        )
        BookAddInputField(
            scope = this,
            label = "Book author",
            isValidated = isValidated,
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
                isValidated = true
                if (bookData.title.isNotEmpty() && bookData.author.isNotEmpty()) {
                    bookData.id =
                        Calendar.getInstance().timeInMillis.toString() + " " + UUID.randomUUID()
                            .toString()
                    mainViewModel.addNewBook(bookData = bookData)
                    navHostController.navigateUp()
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

            var isOpened by remember { mutableStateOf(false) }
            var imageData by remember { mutableStateOf("") }

            GlideImage(
                data = imageData,
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
                    .clickable { isOpened = true }
            )

            ImageAddingDialog(
                isOpened = isOpened,
                onSubmitClick = { imageURL ->
                    imageData = imageURL
                    onImagePreviewChanged(imageURL)
                    isOpened = false
                },
                onDialogClose = {
                    isOpened = false
                }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ImageAddingDialog(
    isOpened: Boolean,
    onSubmitClick: (String) -> Unit,
    onDialogClose: () -> Unit
) {
    if (isOpened) {
        var isExpanded by remember { mutableStateOf(false) }
        var imageURL by remember { mutableStateOf("") }
        AlertDialog(
            title = {
                Text(
                    text = "Choose image loading way",
                    color = AppTheme.colors.text,
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.button,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            },
            buttons = {
                Button(
                    enabled = !isExpanded,
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = AppTheme.colors.card,
                        disabledBackgroundColor = AppTheme.colors.card.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = "Via gallery",
                        color = AppTheme.colors.text,
                        textAlign = TextAlign.Center,
                        style = AppTheme.typography.button,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Button(
                    onClick = { isExpanded = !isExpanded },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = AppTheme.colors.card
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = if (isExpanded) "Cancel" else "Via URL",
                        color = AppTheme.colors.text,
                        textAlign = TextAlign.Center,
                        style = AppTheme.typography.button,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                if (isExpanded) {
                    val keyboardController = LocalSoftwareKeyboardController.current
                    var isValidated by remember { mutableStateOf(false) }
                    OutlinedTextField(
                        value = imageURL,
                        onValueChange = {
                            imageURL = it
                        },
                        isError = imageURL.isEmpty() && isValidated,
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
                                text = if (imageURL.isEmpty() && isValidated) "Image URL (Not empty)" else "Image URL",
                                color = if (imageURL.isEmpty() && isValidated) AppTheme.colors.error else AppTheme.colors.text,
                                textAlign = TextAlign.Start,
                                style = AppTheme.typography.inputFieldTitle,
                            )
                        },
                        trailingIcon = {
                            if (imageURL.isNotEmpty()) {
                                IconButton(
                                    onClick = {
                                        imageURL = ""
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    Button(
                        onClick = {
                            isValidated = true
                            onSubmitClick(imageURL)
                            //if (imageURL.isNotEmpty()) navHostController.navigateUp()
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = AppTheme.colors.card
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                    ) {
                        Text(
                            text = "Submit",
                            color = AppTheme.colors.text,
                            textAlign = TextAlign.Center,
                            style = AppTheme.typography.button,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            },
            onDismissRequest = {
                onDialogClose()
            },
            backgroundColor = AppTheme.colors.background,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .animateContentSize()
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BookAddInputField(
    scope: ColumnScope,
    label: String,
    isValidated: Boolean = false,
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
                isError = inputValue.isEmpty() && isValidated,
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
                        text = if (inputValue.isEmpty() && isValidated) "$label (Not empty)" else label,
                        color = if (inputValue.isEmpty() && isValidated) AppTheme.colors.error else AppTheme.colors.text,
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
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