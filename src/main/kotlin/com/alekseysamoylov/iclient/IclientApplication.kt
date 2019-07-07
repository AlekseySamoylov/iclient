package com.alekseysamoylov.iclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import com.alekseysamoylov.integration.soap.GetCountryResponse
import com.alekseysamoylov.iclient.soap.CountryClient
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean


@SpringBootApplication
class IclientApplication {

  @Bean
  fun lookup(soupCountryClient: CountryClient): CommandLineRunner {
    return CommandLineRunner{ args ->
      doSoupRequest(soupCountryClient)
    }
  }

  private fun doSoupRequest(soupCountryClient: CountryClient) {
    val response = soupCountryClient.getCountry("Spain")
    System.err.println(response.country.currency)
  }

}

fun main(args: Array<String>) {
  runApplication<IclientApplication>(*args)
}
