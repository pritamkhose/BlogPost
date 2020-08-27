package com.jet2travel.blogpost

import com.google.gson.Gson
import com.jet2travel.blogpost.data.Blog
import com.jet2travel.blogpost.data.User
import com.jet2travel.blogpost.repository.BlogRepository
import org.hamcrest.CoreMatchers.containsString
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class BlogTest {

    private lateinit var blog: Blog

    @Before
    fun setUp() {
        var gson = Gson()
        blog = gson.fromJson(BlogString, Blog::class.java)
    }

    @Test
    fun test_default_values_fact() {
        assertEquals("1", blog.id)
        assertNotEquals(null , blog.createdAt)

        assertEquals(1, blog.user?.size)
        assertEquals(1, blog.media?.size)
        assertThat(blog.media?.get(0)?.image.toString(), containsString("https://"))
    }

    private var BlogString :  String = "{\n" +
            "\"id\": \"1\",\n" +
            "\"createdAt\": \"2020-04-17T12:13:44.575Z\",\n" +
            "\"content\": \"calculating the program won't do anything, we need to navigate the multi-byte SMS alarm!\",\n" +
            "\"comments\": 8237,\n" +
            "\"likes\": 62648,\n" +
            "\"media\": [\n" +
            "{\n" +
            "\"id\": \"1\",\n" +
            "\"blogId\": \"1\",\n" +
            "\"createdAt\": \"2020-04-16T22:43:18.606Z\",\n" +
            "\"image\": \"https://s3.amazonaws.com/uifaces/faces/twitter/joe_black/128.jpg\",\n" +
            "\"title\": \"maximized system\",\n" +
            "\"url\": \"http://providenci.com\"\n" +
            "}\n" +
            "],\n" +
            "\"user\": [\n" +
            "{\n" +
            "\"id\": \"1\",\n" +
            "\"blogId\": \"1\",\n" +
            "\"createdAt\": \"2020-04-16T20:17:42.437Z\",\n" +
            "\"name\": \"Dayton\",\n" +
            "\"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/erwanhesry/128.jpg\",\n" +
            "\"lastname\": \"Haag\",\n" +
            "\"city\": \"West Ima\",\n" +
            "\"designation\": \"Human Group Assistant\",\n" +
            "\"about\": \"Try to calculate the SDD bandwidth, maybe it will override the auxiliary card!\"\n" +
            "}\n" +
            "]\n" +
            "}"


}