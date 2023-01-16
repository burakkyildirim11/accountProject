package com.spring.account.dto

import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class CreateAccountRequest(
        @field:NotBlank(message = "Customer id field cannot be empty!")
        val customerId: String,
        @field:Min(0,message= "The initial credit field must be 0 or greater than 0.")
        val initialCredit: BigDecimal
)