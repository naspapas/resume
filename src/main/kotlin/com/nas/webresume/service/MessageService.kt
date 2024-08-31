package com.nas.webresume.service

import com.nas.webresume.model.Message
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageService(val db: JdbcTemplate) {

    fun findMessages(): List<Message> = db.query("SELECT * FROM messages") { response, _ ->
        Message(response.getString("id"), response.getString("text"))
    }

    fun findMessageById(id: String): List<Message> = db.query("SELECT * FROM messages WHERE id = ?", id) { response, _ ->
        Message(response.getString("id"), response.getString("text"))
    }

    fun wipeMessages(): Int {
        return db.update("DELETE FROM messages")
    }

    fun save(message: Message) {
        val id = message.id ?: UUID.randomUUID().toString()
        db.update(
            "INSERT INTO messages VALUES ( ?, ? )",
            id, message.text
        )
    }
}