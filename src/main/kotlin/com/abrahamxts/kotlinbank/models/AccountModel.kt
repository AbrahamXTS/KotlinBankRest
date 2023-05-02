package com.abrahamxts.kotlinbank.models

import javax.persistence.*

@Entity
@Table(name = "account")
class AccountModel private constructor(builder: Builder) {

    @Id
    @Column(name = "account_number", nullable = false)
    var accountNumber: String

    @ManyToOne
    @JoinColumn(name = "client_number", nullable = false)
    var clientNumber: CustomerModel

    @Column(name = "balance", nullable = false)
    var balance: Double

    init {
        this.clientNumber = builder.clientNumber!!
        this.accountNumber = builder.accountNumber
        this.balance = builder.balance
    }

    class Builder {
        var clientNumber: CustomerModel? = null
        var accountNumber: String = ""
        var balance: Double = 0.0

        fun clientNumber(clientNumber: CustomerModel): Builder {
            this.clientNumber = clientNumber
            return this
        }

        fun accountNumber(accountNumber: String): Builder {
            this.accountNumber = accountNumber
            return this
        }

        fun balance(balance: Double): Builder {
            this.balance = balance
            return this
        }

        fun build(): AccountModel {
            return AccountModel(this)
        }
    }
}