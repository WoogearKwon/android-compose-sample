package net.huray.basiccodelab.data.posts.impl

import net.huray.basiccodelab.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import net.huray.basiccodelab.model.Post
import net.huray.basiccodelab.model.PostsFeed
import net.huray.basiccodelab.data.posts.PostsRepository
import net.huray.basiccodelab.utils.addOrRemove
import java.lang.IllegalStateException

class FakePostsRepository : PostsRepository {
    private val favorites = MutableStateFlow<Set<String>>(setOf())

    private val mutex = Mutex()

    override suspend fun getPost(postId: String?): Result<Post> {
        return withContext(Dispatchers.IO) {
            val post = posts.allPosts.find { it.id == postId }
            if (post == null) {
                Result.Error(IllegalArgumentException("Post not found"))
            } else {
                Result.Success(post)
            }
        }
    }

    override suspend fun getPostsFeed(): Result<PostsFeed> {
        return withContext(Dispatchers.IO) {
            delay(800)

            if (shouldRandomlyFail()) {
                Result.Error(IllegalStateException())
            } else {
                Result.Success(posts)
            }
        }
    }

    override fun observeFavorites(): Flow<Set<String>>  = favorites

    override suspend fun toggleFavorite(postId: String) {
        mutex.withLock {
            val set = favorites.value.toMutableSet()
            set.addOrRemove(postId)
            favorites.value = set.toSet()
        }
    }

    private var requestCount = 0

    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0
}