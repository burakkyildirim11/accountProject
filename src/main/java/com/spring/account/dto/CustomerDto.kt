package com.spring.account.dto

class CustomerDto (
        val id: String,
        val name: String,
        val surname: String,
        val accounts: Set<CustomerAccountDto>

        ) {

}