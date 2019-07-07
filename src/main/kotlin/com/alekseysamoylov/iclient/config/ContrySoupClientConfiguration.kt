package com.alekseysamoylov.iclient.config

import org.springframework.context.annotation.Configuration
import com.alekseysamoylov.iclient.soap.CountryClient
import org.springframework.context.annotation.Bean
import org.springframework.oxm.jaxb.Jaxb2Marshaller



@Configuration
class ContrySoupClientConfiguration {
  @Bean
  fun marshaller(): Jaxb2Marshaller {
    val marshaller = Jaxb2Marshaller()
    // package with generated classes based on wsdl
    marshaller.contextPath = "com.alekseysamoylov.integration.soap"
    return marshaller
  }

  @Bean
  fun countryClient(marshaller: Jaxb2Marshaller): CountryClient {
    val client = CountryClient()
    client.defaultUri = "http://localhost:8080/ws"
    client.marshaller = marshaller
    client.unmarshaller = marshaller
    return client
  }
}
