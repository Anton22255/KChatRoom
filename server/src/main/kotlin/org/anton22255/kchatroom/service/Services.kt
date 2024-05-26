package org.anton22255.kchatroom.service

import org.anton22255.kchatroom.repository.Repositories

/**
 * Service locator for services.
 */
object Services {
    val chat: ChatService by lazy { ChatService(Repositories.chat, Repositories.room, Repositories.user) }
}