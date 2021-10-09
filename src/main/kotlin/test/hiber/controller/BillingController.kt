package test.hiber.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import test.hiber.service.BillingService

@RestController
@RequestMapping("/billing")
class BillingController(
    private val billingService: BillingService
) {

    @GetMapping("/create-billing")
    fun createDetails() = billingService.createDetails()

    @GetMapping("/create-billing2")
    fun createDetails2() = billingService.createDetails2()
}