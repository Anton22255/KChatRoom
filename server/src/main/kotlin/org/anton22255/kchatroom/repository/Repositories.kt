package org.anton22255.kchatroom.repository

import org.anton22255.kchatroom.repository.*

/**
 * Service locator for repositories.
 *
 */
object Repositories {
    val chat: ChatRepository by lazy { ChatRepositoryImpl() }
    val room: ChatRoomRepository by lazy { ChatRoomRepositoryImpl() }
    val user: ChatUserRepository by lazy { ChatUserRepositoryImpl() }
}