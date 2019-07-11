package com.alekseysamoylov.iclient.rest

import com.alekseysamoylov.iclient.Article
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


@FeignClient(value = "jplaceholder", url = "http://localhost:8080/api")
interface MyFeignClient {

  @RequestMapping(method = [RequestMethod.GET], value = ["/article"])
  fun getArticles(): List<Article>

  @RequestMapping(method = [RequestMethod.GET], value = ["/article/{slug}"], produces = ["application/json"])
  fun getByArticle(@PathVariable("slug") slug: String?): Article
}
