package com.example.retrofit.domain

import com.example.retrofit.data.storage.NewsEntity
import com.example.retrofit.network.dto.Article
import org.jsoup.Jsoup
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun NewsEntity.toNews() =
    News(
        title = title,
        author = author,
        text = text,
        date = date,
        isIconClicked = isIconClicked,
        iconUrl = iconUrl,
        articleUrl = articleUrl
    )

fun News.toNewsEntity() =
    NewsEntity(
        title = title,
        author = author,
        text = text,
        date = date,
        isIconClicked = isIconClicked,
        iconUrl = iconUrl,
        articleUrl = articleUrl
    )

fun Article.toNews() =
    News(
        title = title.orEmpty(),
        author = author.orEmpty(),
        text = htmlToText(description.orEmpty()),
        date = formatDate(publishedAt),
        isIconClicked = false,
        iconUrl = previewUrl.orEmpty(),
        articleUrl = articleUrl.orEmpty()
    )

fun htmlToText(html: String): String {
    return Jsoup.parse(html).text()
}

fun formatDate(string: String?): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val date = ZonedDateTime.parse(string)
    return date.format(formatter)
}



