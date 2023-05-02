package com.abrahamxts.kotlinbank.useCases

import com.abrahamxts.kotlinbank.dto.CustomerDTO
import com.abrahamxts.kotlinbank.http.APICurpClient
import com.abrahamxts.kotlinbank.models.*
import com.abrahamxts.kotlinbank.repositories.CustomerRepositorie
import org.springframework.stereotype.Service

@Service
class CreateCustomer(
    private val customerRepositorie: CustomerRepositorie,
    private val createAccount: CreateAccount
) {

    @Throws(Error::class)
    fun run(curp: String): CustomerDTO {
        val personInfo = APICurpClient().consultarInfoPorCurp(curp)
            ?: throw Error("¡Oh no! No hemos podido generar tu información con la curp proporcionada.\nPor favor, verifica tu información e intenta más tarde.")

        if (customerRepositorie.findByCurp(curp) != null)
            throw Error("Ya existe un cliente con la información proporcionada.")

        val clientNumber = this.generateClientNumber(personInfo)

        val customer = CustomerModel.Builder()
            .clientNumber(clientNumber)
            .name(personInfo.name)
            .paternalSurname(personInfo.paternalSurname)
            .maternalSurname(personInfo.maternalSurname)
            .curp(personInfo.curp)
            .gender(personInfo.gender)
            .birthDate(personInfo.birthDate)
            .birthEntity(personInfo.birthEntity)
            .build()

        customerRepositorie.save(customer)

        val newAccount = createAccount.run(curp)

        return CustomerDTO(customer, listOf(newAccount))
    }

    private fun generateClientNumber(personInfo: PersonModel): String =
        "${personInfo.birthDate.filter { c -> c != '/' }}${List(8) { ('0'..'9').random() }.joinToString("")}"
}
