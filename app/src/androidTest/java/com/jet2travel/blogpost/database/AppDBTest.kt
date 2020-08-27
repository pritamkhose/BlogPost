package com.jet2travel.blogpost.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.jet2travel.blogpost.data.Blog
import com.jet2travel.blogpost.data.Media
import com.jet2travel.blogpost.data.User
import com.jet2travel.blogpost.repository.BlogRepository
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlogsDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var appDao: AppDao
    private var aList = mutableListOf<Blog>()
    private lateinit var blog: Blog
    private var gson = Gson()

    @Before
    fun setUp() {
        blog = gson.fromJson(BlogString, Blog::class.java)
        aList.add(blog)
        var blog2 = blog
        blog2.id = "2"
        aList.add(blog2)
    }

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        appDao = database.appDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetAllBlogs() {
        // Insert test data
        appDao.insert(covertBlogType(aList))
        val aList = appDao.getAllBlogs()
        Assert.assertThat(aList.size, Matchers.equalTo(1))
    }

    private fun covertBlogType(vbody: MutableList<Blog>): MutableList<com.jet2travel.blogpost.database.model.Blog> {
        val rows = mutableListOf<com.jet2travel.blogpost.database.model.Blog>()
        vbody.forEach { blog ->
           rows.add(
                com.jet2travel.blogpost.database.model.Blog(
                    blog.id.toInt(),
                    blog.comments,
                    blog.content,
                    blog.createdAt,
                    blog.likes,
                    blog.media.toString(),
                    blog.user.toString()
                )
            )
        }
        return rows
    }

    @Test
    fun testGetListSize() {
        Assert.assertEquals(2, aList?.size)
    }

    @Test
    fun testDeleteAllBlogs() {
        // Insert test data
        appDao.insert(covertBlogType(aList))

        Assert.assertThat(appDao.getAllBlogs().size, Matchers.equalTo(1))
        // Delete test data
        appDao.deleteAllBlogs()
        Assert.assertThat(appDao.getAllBlogs().size, Matchers.equalTo(0))
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

