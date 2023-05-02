package com.abrahamxts.kotlinbank.repositories

import com.abrahamxts.kotlinbank.models.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepositorie : JpaRepository<AccountModel, String> {
    fun findAllByClientNumber(clientNumber: CustomerModel): List<AccountModel>
    fun findByAccountNumber(accountNumber: String): AccountModel?
}