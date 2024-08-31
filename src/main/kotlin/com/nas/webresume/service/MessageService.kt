package com.nas.webresume.service


import com.nas.webresume.model.Message
import com.nas.webresume.repository.MessageRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class MessageService(private val messageRepository: MessageRepository) {

    fun findMessages(): List<Message> {
        return messageRepository.findAll()
    }

    fun findMessageById(id: UUID): Message {
        val message = messageRepository.findById(id)
       if (message.isPresent){
           return message.get()
       }
        else {
            throw RuntimeException("Message not found")
       }
    }

    fun wipeMessages(): Long {
        val count = messageRepository.count()
        messageRepository.deleteAll()
        return count
    }

    fun save(message: Message): Message {
        return messageRepository.save(message)
    }
}