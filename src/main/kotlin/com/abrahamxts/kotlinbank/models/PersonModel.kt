package com.abrahamxts.kotlinbank.models

class PersonModel private constructor(builder: Builder) {
    var name: String
    var paternalSurname: String
    var maternalSurname: String
    var curp: String
    var gender: String
    var birthDate: String
    var birthEntity: String

    init {
        this.name = builder.name
        this.paternalSurname = builder.paternalSurname
        this.maternalSurname = builder.maternalSurname
        this.curp = builder.curp
        this.gender = builder.gender
        this.birthDate = builder.birthDate
        this.birthEntity = builder.birthEntity
    }

    class Builder {
        var name: String = ""
        var paternalSurname: String = ""
        var maternalSurname: String = ""
        var curp: String = ""
        var gender: String = ""
        var birthDate: String = ""
        var birthEntity: String = ""

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

        fun build(): PersonModel {
            return PersonModel(this)
        }
    }
}