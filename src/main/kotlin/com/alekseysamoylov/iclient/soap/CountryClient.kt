package com.alekseysamoylov.iclient.soap

import org.springframework.ws.client.core.support.WebServiceGatewaySupport
import org.springframework.ws.soap.client.core.SoapActionCallback
import com.alekseysamoylov.integration.soap.GetCountryResponse
import com.alekseysamoylov.integration.soap.GetCountryRequest
import org.slf4j.LoggerFactory


class CountryClient : WebServiceGatewaySupport() {

  fun getCountry(country: String): GetCountryResponse {

    val request = GetCountryRequest()
    request.name = country

    log.info("Requesting location for $country")

    val response = webServiceTemplate
        .marshalSendAndReceive("http://localhost:8080/ws/countries", request,
            SoapActionCallback(
                "http://com.alekseysamoylov/ws/getCountryRequest")) as GetCountryResponse

    log.info("Response: $response")

    return response
  }


}

private val log = LoggerFactory.getLogger(CountryClient::class.java)
