package com.abrahamxts.kotlinbank.repositories

import com.abrahamxts.kotlinbank.models.CustomerModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepositorie : JpaRepository<CustomerModel, String> {
    fun findByCurp(curp: String): CustomerModel?
    fun findByClientNumber(clientNumber: String): CustomerModel?
}