package com.abrahamxts.kotlinbank.dto

import com.abrahamxts.kotlinbank.models.*

public data class CustomerDTO(
    val customer: CustomerModel,
    val accounts: List<AccountModel>
) {}