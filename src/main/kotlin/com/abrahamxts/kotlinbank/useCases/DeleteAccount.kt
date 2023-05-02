package com.abrahamxts.kotlinbank.useCases

import org.springframework.stereotype.Service
import com.abrahamxts.kotlinbank.repositories.AccountRepositorie

@Service
class DeleteAccount(private val accountRepositorie: AccountRepositorie) {

    @Throws(Error::class)
    fun run(accountNumber: String): String {

        val account = accountRepositorie.findByAccountNumber(accountNumber)
            ?: throw Error("No hay ninguna cuenta existente que coincida con el número de cuenta recibido.")

        accountRepositorie.delete(account)

        return "Cuenta ${account.accountNumber} eliminada correctamente."
    }
}