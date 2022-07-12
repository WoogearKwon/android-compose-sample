package net.huray.basiccodelab.data.posts

import net.huray.basiccodelab.data.Result
import kotlinx.coroutines.flow.Flow
import net.huray.basiccodelab.model.Post
import net.huray.basiccodelab.model.PostsFeed

interface PostsRepository {

    suspend fun getPost(postId: String?): Result<Post>

    suspend fun getPostsFeed(): Result<PostsFeed>

    fun observeFavorites(): Flow<Set<String>>

    suspend fun toggleFavorite(postId: String)
}