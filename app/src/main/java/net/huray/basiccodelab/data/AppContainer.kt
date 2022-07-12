package net.huray.basiccodelab.data

import android.content.Context
import net.huray.basiccodelab.data.interests.InterestsRepository
import net.huray.basiccodelab.data.interests.impl.FakeInterestsRepository
import net.huray.basiccodelab.data.posts.PostsRepository
import net.huray.basiccodelab.data.posts.impl.FakePostsRepository

interface AppContainer {
    val postRepository: PostsRepository
    val interestsRepository: InterestsRepository
}

class AppContainerImpl(private val applicationContext: Context) : AppContainer {

    override val postRepository: PostsRepository by lazy {
        FakePostsRepository()
    }

    override val interestsRepository: InterestsRepository by lazy {
        FakeInterestsRepository()
    }
}