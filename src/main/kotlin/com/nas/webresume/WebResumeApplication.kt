package com.nas.webresume

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate
import java.util.UUID
import org.springframework.jdbc.core.query
import org.springframework.web.bind.annotation.*


@SpringBootApplication
class WebResumeApplication

fun main(args: Array<String>) {
	runApplication<WebResumeApplication>(*args)
}

@RestController
class MessageController(val service: MessageService) {
	@GetMapping("/")
	fun index(){
		print("Index hit")
	}

	@GetMapping("/{id}")
	fun index(@PathVariable id: String): List<Message> =
		service.findMessageById(id)

	@GetMapping("/list")
	fun list(): List<Message> = service.findMessages()

	@GetMapping("/wipeit")
	fun deleteAll(){
		service.wipeMessages()
	}

	@PostMapping("/")
	fun post(@RequestBody message: Message) {
		service.save(message)
	}
}

data class Message (val id: String?, val text: String)


@Service
class MessageService(val db: JdbcTemplate) {

	fun findMessages(): List<Message> = db.query("	SELECT * FROM messages") { response, _ ->
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