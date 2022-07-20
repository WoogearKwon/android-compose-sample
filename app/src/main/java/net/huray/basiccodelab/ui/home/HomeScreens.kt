package net.huray.basiccodelab.ui.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.huray.basiccodelab.R
import net.huray.basiccodelab.model.Post
import net.huray.basiccodelab.ui.modifiers.interceptKey
import net.huray.basiccodelab.utils.BookmarkButton
import net.huray.basiccodelab.utils.FavoriteButton
import net.huray.basiccodelab.utils.ShareButton
import net.huray.basiccodelab.utils.TextSettingButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeFeedWithArticleDetailsScreen(

) {

}

@Composable
private fun HomeScreenWithList(

) {

}

@Composable
private fun LoadingContent() {

}

@Composable
private fun PostList() {

}

@Composable
private fun FullScreenLoading() {

}

@Composable
private fun PostListTopSection() {

}

@Composable
private fun PostListSimpleSection() {

}

@Composable
private fun PostListPopularSection() {

}

@Composable
private fun PostListHistorySection(
    posts: List<Post>,
    navigateToArticle: (String) -> Unit
) {
    Column {
        posts.forEach { post ->
            // todo
        }
    }
}

@Composable
private fun PostListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun HomeSearch(
    modifier: Modifier = Modifier,
    searchInput: String = "",
    onSearchInputChanged: (String) -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(Dp.Hairline, MaterialTheme.colors.onSurface.copy(alpha = .6f)),
        elevation = 4.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(horizontal = 8.dp)
        ) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                IconButton(onClick = { /* Functionally not supported yet */ }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(id = R.string.cd_search)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                val context = LocalContext.current
                val focusManager = LocalFocusManager.current
                val keyboardController = LocalSoftwareKeyboardController.current
                TextField(
                    value = searchInput,
                    onValueChange = { onSearchInputChanged(it) },
                    placeholder = { Text(stringResource(id = R.string.home_search)) },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            submitSearch(onSearchInputChanged, context)
                            keyboardController?.hide()
                        }
                    ),
                    modifier = Modifier
                        .interceptKey(Key.Enter) {
                            submitSearch(onSearchInputChanged, context)
                        }
                        .interceptKey(Key.Escape) {
                            focusManager.clearFocus()
                        }
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /* Functionally not supported yet */ }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = stringResource(R.string.cd_more_actions)
                    )
                }
            }
        }
    }
}

private fun submitSearch(
    onSearchInputChanged: (String) -> Unit,
    context: Context,
) {
    onSearchInputChanged("")
    Toast.makeText(context, "Search is not yet implemented", Toast.LENGTH_SHORT).show()
}

@Composable
private fun PostTopBar(
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onSharePost: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(Dp.Hairline, MaterialTheme.colors.onSurface.copy(alpha = .6f)),
        modifier = modifier.padding(end = 16.dp)
    ) {
        Row(Modifier.padding(horizontal = 8.dp)) {
            FavoriteButton { /* Functionally not available */ }
            BookmarkButton(isBookmarked = isFavorite, onClick = onToggleFavorite)
            ShareButton(onClick = onSharePost)
            TextSettingButton { /* Functionally not available */ }
        }
    }
}

@Composable
private fun HomeTopAppBar(
    elevation: Dp,
    openDrawer: () -> Unit
) {
    val title = stringResource(id = R.string.app_name)
    TopAppBar(
        title = {
            Icon(
                painter = painterResource(id = R.drawable.ic_jetnews_wordmark),
                contentDescription = title,
                tint = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 4.dp, top = 10.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_jetnews_wordmark),
                    contentDescription = stringResource(id = R.string.cd_open_navigation_drawer),
                    tint = MaterialTheme.colors.primary
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(id = R.string.cd_search)
                )
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = elevation
    )
}