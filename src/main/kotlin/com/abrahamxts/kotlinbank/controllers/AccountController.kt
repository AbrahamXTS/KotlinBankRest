package com.abrahamxts.kotlinbank.controllers

import com.abrahamxts.kotlinbank.models.AccountModel
import com.abrahamxts.kotlinbank.useCases.CreateAccount
import com.abrahamxts.kotlinbank.useCases.DeleteAccount
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("accounts")
@CrossOrigin(
    origins = ["*"],
    methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE]
)
class AccountController(
    private val createAccount: CreateAccount,
    private val deleteAccount: DeleteAccount
) {

    data class AddAccountBody(val curp: String)

    @PostMapping("add")
    fun addAccount(@RequestBody addAccountBody: AddAccountBody): ResponseEntity<AccountModel> {
        val account = this.createAccount.run(addAccountBody.curp)
        return ResponseEntity<AccountModel>(account, HttpStatus.CREATED)
    }

    data class DeleteAccountBody(val accountNumber: String)

    @DeleteMapping("delete")
    fun deleteAccount(@RequestBody deleteAccountBody: DeleteAccountBody): ResponseEntity<String> {
        val message = this.deleteAccount.run(deleteAccountBody.accountNumber)
        return ResponseEntity<String>(message, HttpStatus.OK)
    }
}