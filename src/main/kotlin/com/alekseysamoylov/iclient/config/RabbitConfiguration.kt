package com.alekseysamoylov.iclient.config

import com.alekseysamoylov.iclient.rabbit.UserErrorMessageListener
import com.alekseysamoylov.iclient.rabbit.UserMessageListener
import org.springframework.amqp.core.AmqpAdmin
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitConfiguration {

  @Bean
  fun connectionFactory(): ConnectionFactory {
    val connectionFactory = CachingConnectionFactory("localhost")
    connectionFactory.username = "rabbitmq"
    connectionFactory.setPassword("rabbitmq")
    return connectionFactory
  }

  @Bean
  fun amqpAdmin(): AmqpAdmin {
    return RabbitAdmin(connectionFactory())
  }

  @Bean
  fun rabbitTemplate(): RabbitTemplate {
    return RabbitTemplate(connectionFactory())
  }

  @Bean
  fun messageListenerContainer1(): SimpleMessageListenerContainer {
    val container = SimpleMessageListenerContainer()
    container.connectionFactory = connectionFactory()
    container.setQueueNames("user-important")
    container.setMessageListener(UserMessageListener())
    return container
  }

  @Bean
  fun messageListenerContainer2(): SimpleMessageListenerContainer {
    val container = SimpleMessageListenerContainer()
    container.connectionFactory = connectionFactory()
    container.setQueueNames("user-error")
    container.setMessageListener(UserErrorMessageListener())
    return container
  }
}
