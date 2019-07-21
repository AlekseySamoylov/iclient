package com.alekseysamoylov.iclient.rabbit

import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageListener


class UserMessageListener : MessageListener {
  override fun onMessage(message: Message?) {
    println("Hello $message")
  }
}
class UserErrorMessageListener : MessageListener {
  override fun onMessage(message: Message?) {
    println("Error $message")
  }
}
