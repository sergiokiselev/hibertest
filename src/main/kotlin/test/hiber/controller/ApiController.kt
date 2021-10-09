package test.hiber.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import test.hiber.service.ApiService

@RestController
class ApiController(
    private val apiService: ApiService
) {

    @GetMapping("create-item")
    fun createItem() = apiService.createItem()

    @GetMapping("items")
    fun getItems() = apiService.getItems()

    @GetMapping("message")
    fun createMessage() = apiService.createMessage()

    @GetMapping("summary")
    fun getSummary() = apiService.getSummary()

    @GetMapping("create-user")
    fun createUser() = apiService.createUser()
}