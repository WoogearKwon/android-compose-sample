package net.huray.basiccodelab.data.posts.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import net.huray.basiccodelab.data.Result
import net.huray.basiccodelab.data.posts.PostsRepository
import net.huray.basiccodelab.model.Post
import net.huray.basiccodelab.model.PostsFeed
import net.huray.basiccodelab.utils.addOrRemove

class BlockingFakePostsRepository : PostsRepository {

    private val favorites = MutableStateFlow<Set<String>>(setOf())

    override suspend fun getPost(postId: String?): Result<Post> {
        return withContext(Dispatchers.IO) {
            val post = posts.allPosts.find { it.id == postId }
            if (post == null) {
                Result.Error(IllegalArgumentException("Unable to find post"))
            } else {
                Result.Success(post)
            }
        }
    }

    override suspend fun getPostsFeed(): Result<PostsFeed> {
        return Result.Success(posts)
    }

    override fun observeFavorites(): Flow<Set<String>> = favorites

    override suspend fun toggleFavorite(postId: String) {
        val set = favorites.value.toMutableSet()
        set.addOrRemove(postId)
        favorites.value = set
    }


}