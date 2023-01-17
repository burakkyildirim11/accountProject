package com.spring.account.dto

import javax.validation.constraints.NotBlank

data class CreateCustomerRequest(

        @field:NotBlank(message = "Customer name field cannot be empty!")
        val name: String,
        @field:NotBlank(message = "Customer surname field cannot be empty!")
        val surname: String
)
