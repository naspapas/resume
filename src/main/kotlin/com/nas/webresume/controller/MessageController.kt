package com.nas.webresume.controller



import com.nas.webresume.model.Message
import com.nas.webresume.service.MessageService
import org.springframework.web.bind.annotation.*

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