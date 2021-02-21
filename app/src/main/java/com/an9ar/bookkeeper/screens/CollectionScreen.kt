package com.an9ar.bookkeeper.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.an9ar.bookkeeper.R
import com.an9ar.bookkeeper.data.BookModel
import com.an9ar.bookkeeper.theme.AppTheme
import com.an9ar.bookkeeper.viewmodels.MainViewModel
import dev.chrisbanes.accompanist.glide.GlideImage
import dev.chrisbanes.accompanist.insets.LocalWindowInsets
import dev.chrisbanes.accompanist.insets.toPaddingValues

@Composable
fun CollectionScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    Scaffold(
        topBar = {
            CollectionScreenToolbar(navHostController = navHostController)
        }
    ) {
        CollectionScreenContent(
            navHostController = navHostController,
            mainViewModel = mainViewModel
        )
    }
}

@Composable
fun CollectionScreenToolbar(
    navHostController: NavHostController
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                LocalWindowInsets.current.systemBars
                    .toPaddingValues(
                        bottom = false,
                        additionalTop = 8.dp,
                        additionalBottom = 8.dp
                    )
            )
    ) {
        val (menuButton, titleBlock, addButton) = createRefs()

        IconButton(
            onClick = {
                navHostController.navigate(Screens.MenuScreen.routeName)
            },
            modifier = Modifier.constrainAs(menuButton) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start, 8.dp)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu button",
                tint = AppTheme.colors.uiSurface
            )
        }
        Text(
            text = "Books collections",
            color = AppTheme.colors.text,
            style = AppTheme.typography.h6,
            modifier = Modifier.constrainAs(titleBlock) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        IconButton(
            onClick = {

            },
            modifier = Modifier.constrainAs(addButton) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end, 8.dp)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add button",
                tint = AppTheme.colors.uiSurface
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CollectionScreenContent(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        items(mainViewModel.mockBooks) { item ->
            BookItem(bookModel = item)
        }
    }
}

@Composable
fun BookItem(
    bookModel: BookModel
) {
    Card(
        backgroundColor = AppTheme.colors.card,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable{

            }
            .fillMaxWidth()
            .preferredHeight(208.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (bookModel.previewUrl.isEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().weight(0.75f).padding(bottom = 2.dp)
                )
            }
            else {
                GlideImage(
                    data = bookModel.previewUrl,
                    contentDescription = null,
                    fadeIn = true,
                    contentScale = ContentScale.Crop,
                    loading = {
                        Box(Modifier.fillMaxSize()) {
                            CircularProgressIndicator(
                                color = AppTheme.colors.warning,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    },
                    error = {
                        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = null)
                    },
                    modifier = Modifier.weight(0.75f).padding(bottom = 2.dp)
                )
            }
            Text(
                text = bookModel.title,
                style = AppTheme.typography.bookItemTitle,
                color = AppTheme.colors.text,
                softWrap = false,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(0.125f)
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 2.dp, start = 8.dp, end = 8.dp)
            )
            Text(
                text = bookModel.author,
                style = AppTheme.typography.bookItemAuthor,
                color = AppTheme.colors.textSecondary,
                softWrap = false,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(0.125f)
                    .fillMaxWidth()
                    .padding(top = 2.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
            )
        }
    }
}
