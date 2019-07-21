package com.alekseysamoylov.iclient

import com.alekseysamoylov.iclient.rest.MyFeignClient
import com.alekseysamoylov.iclient.soap.CountryClient
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean


@EnableFeignClients
@SpringBootApplication
class IclientApplication {

  @Bean
  fun lookup(
      soupCountryClient: CountryClient,
      myFeignClient: MyFeignClient
  ): CommandLineRunner {
    return CommandLineRunner{ args ->
      doSoupRequest(soupCountryClient)
      doHttpRequest(myFeignClient)
      doRabbitMqMessageWait()
    }
  }

  private fun doRabbitMqMessageWait() {
    while (true) {
      Thread.sleep(5000)
//      println("Waiting for the messages from a queue")
    }
  }

  private fun doHttpRequest(myFeignClient: MyFeignClient) {
    val articles = myFeignClient.getArticles()
    println("Feign http response: $articles")
  }

  private fun doSoupRequest(soupCountryClient: CountryClient) {
    val response = soupCountryClient.getCountry("Spain")
    System.err.println(response.country.currency)
  }

}

fun main(args: Array<String>) {
  runApplication<IclientApplication>(*args)
}
