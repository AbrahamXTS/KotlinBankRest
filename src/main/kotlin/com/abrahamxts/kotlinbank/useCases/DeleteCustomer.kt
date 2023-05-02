package com.abrahamxts.kotlinbank.useCases

import org.springframework.stereotype.Service
import com.abrahamxts.kotlinbank.repositories.*

@Service
class DeleteCustomer(
    private val accountRepositorie: AccountRepositorie,
    private val customerRepositorie: CustomerRepositorie
) {

    @Throws(Error::class)
    fun run(clientNumber: String): String {

        val customer = customerRepositorie.findByClientNumber(clientNumber)
            ?: throw Error("No hay ningun cliente existente que coincida con el número recibido.")

        if (accountRepositorie.findAllByClientNumber(customer).isNotEmpty()) {
            throw Error("El cliente no se puede eliminar debido a que existen cuentas asociadas a el.")
        }

        customerRepositorie.delete(customer)

        return "El número de cliente ${customer.clientNumber} y la información asociada al cliente: ${customer.name} ${customer.paternalSurname} ${customer.maternalSurname} ha sido eliminada correctamente."
    }
}