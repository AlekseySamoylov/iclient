package com.alekseysamoylov.iclient

import com.alekseysamoylov.iclient.rest.MyFeignClient
import com.alekseysamoylov.iclient.soap.CountryClient
import com.alekseysamoylov.integration.model.ProtobufTraining
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.web.client.RestTemplate


@EnableFeignClients
@SpringBootApplication
class IclientApplication {

  @Bean
  fun lookup(
      soupCountryClient: CountryClient,
      myFeignClient: MyFeignClient,
      protobufHttpMessageConverter: ProtobufHttpMessageConverter
  ): CommandLineRunner {
    return CommandLineRunner{ args ->
      while (true) {
        doSoupRequest(soupCountryClient)
        doHttpRequest(myFeignClient)
        doProtobufRequest(protobufHttpMessageConverter)
        Thread.sleep(5000)
      }
    }
  }

  private fun doHttpRequest(myFeignClient: MyFeignClient) {
    val articles = myFeignClient.getArticles()
    println("Feign http response: $articles")
  }

  private fun doProtobufRequest(protobufHttpMessageConverter: ProtobufHttpMessageConverter) {
    val restTemplate = RestTemplate();
    restTemplate.messageConverters.add(protobufHttpMessageConverter)
    val course = restTemplate.getForEntity("http://localhost:8080/api/course/1", ProtobufTraining.Course::class.java)
    println("Protobuf http response: $course")
  }

  private fun doSoupRequest(soupCountryClient: CountryClient) {
    val response = soupCountryClient.getCountry("Spain")
    System.err.println(response.country.currency)
  }

}

fun main(args: Array<String>) {
  runApplication<IclientApplication>(*args)
}
