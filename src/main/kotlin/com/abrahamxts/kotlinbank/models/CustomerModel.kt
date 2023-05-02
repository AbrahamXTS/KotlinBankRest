package com.abrahamxts.kotlinbank.models

import javax.persistence.*

@Entity
@Table(name = "customer")
class CustomerModel private constructor(builder: Builder) {

    @Id
    @Column(name = "client_number", nullable = false)
    var clientNumber: String

    @Column(name = "name", nullable = false)
    var name: String

    @Column(name = "paternal_surname", nullable = false)
    var paternalSurname: String

    @Column(name = "maternal_surname", nullable = false)
    var maternalSurname: String

    @Column(name = "curp", nullable = false)
    var curp: String

    @Column(name = "gender", nullable = false)
    var gender: String

    @Column(name = "birth_date", nullable = false)
    var birthDate: String

    @Column(name = "birth_entity", nullable = false)
    var birthEntity: String

    init {
        this.clientNumber = builder.clientNumber
        this.name = builder.name
        this.paternalSurname = builder.paternalSurname
        this.maternalSurname = builder.maternalSurname
        this.curp = builder.curp
        this.gender = builder.gender
        this.birthDate = builder.birthDate
        this.birthEntity = builder.birthEntity
    }

    class Builder {
        var clientNumber: String = ""
        var name: String = ""
        var paternalSurname: String = ""
        var maternalSurname: String = ""
        var curp: String = ""
        var gender: String = ""
        var birthDate: String = ""
        var birthEntity: String = ""

        fun clientNumber(clientNumber: String): Builder {
            this.clientNumber = clientNumber
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun paternalSurname(paternalSurname: String): Builder {
            this.paternalSurname = paternalSurname
            return this
        }

        fun maternalSurname(maternalSurname: String): Builder {
            this.maternalSurname = maternalSurname
            return this
        }

        fun curp(curp: String): Builder {
            this.curp = curp
            return this
        }

        fun gender(gender: String): Builder {
            this.gender = gender
            return this
        }

        fun birthDate(birthDate: String): Builder {
            this.birthDate = birthDate
            return this
        }

        fun birthEntity(birthEntity: String): Builder {
            this.birthEntity = birthEntity
            return this
        }

        fun build(): CustomerModel {
            return CustomerModel(this)
        }
    }
}