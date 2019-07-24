package com.alekseysamoylov.iclient

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne


@Entity
data class Article(
    var title: String = "",
    var headline: String = "",
    var content: String = "",
    @ManyToOne var author: User,
    var slug: String = title.toSlug(),
    @Id @GeneratedValue var id: Long? = null)

@Entity
data class User(
    var login: String = "",
    var firstname: String = "",
    var lastname: String = "",
    var description: String? = null,
    @Id @GeneratedValue var id: Long? = null)
