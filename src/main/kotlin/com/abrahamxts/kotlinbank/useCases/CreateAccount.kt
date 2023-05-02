package com.abrahamxts.kotlinbank.useCases

import org.springframework.stereotype.Service
import com.abrahamxts.kotlinbank.repositories.*
import com.abrahamxts.kotlinbank.models.AccountModel

@Service
class CreateAccount(
    private val customerRepositorie: CustomerRepositorie,
    private val accountRepositorie: AccountRepositorie
) {

    @Throws(Error::class)
    fun run(curp: String): AccountModel {
        val customer = customerRepositorie.findByCurp(curp)
            ?: throw Error("¡Oh no! La curp recibida no parece ser de algún cliente existente. \nPor favor, verifica tu información e intenta más tarde.")

        val newAccount = AccountModel.Builder()
            .clientNumber(customer)
            .accountNumber(this.generateAccountNumber())
            .balance(0.0)
            .build()

        accountRepositorie.save(newAccount)

        return newAccount
    }

    private fun generateAccountNumber(): String =
        List(16) { ('0'..'9').random() }.joinToString("")
}