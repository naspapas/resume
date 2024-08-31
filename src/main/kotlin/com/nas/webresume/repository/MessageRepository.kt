package com.nas.webresume.repository

import com.nas.webresume.model.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository

interface MessageRepository : JpaRepository<Message, UUID> {

}