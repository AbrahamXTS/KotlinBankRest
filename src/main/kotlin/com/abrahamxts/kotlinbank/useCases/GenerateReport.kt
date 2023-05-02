package com.abrahamxts.kotlinbank.useCases

import com.abrahamxts.kotlinbank.dto.CustomerDTO
import com.abrahamxts.kotlinbank.repositories.*
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine
import org.xhtmlrenderer.pdf.ITextRenderer
import java.io.ByteArrayOutputStream

@Service
class GenerateReport(
    private val accountRepositorie: AccountRepositorie,
    private val customerRepositorie: CustomerRepositorie,
    private val templateEngine: SpringTemplateEngine,
) {
    fun run(): ByteArray {
        val customers = getAllCustomers()

        return generatePDF(customers)
    }

    fun getCustomer(clientNumber: String): CustomerDTO {
        val customer = customerRepositorie.findByClientNumber(clientNumber)
            ?: throw Error("Cliente $clientNumber no encontrado")

        return CustomerDTO(
            customer,
            accountRepositorie.findAllByClientNumber(customer)
        )
    }

    fun getAllCustomers(): List<CustomerDTO> {
        return customerRepositorie
            .findAll()
            .map { customer -> CustomerDTO(customer, accountRepositorie.findAllByClientNumber(customer)) }
    }

    private fun generatePDF(customers: List<CustomerDTO>): ByteArray {
        val context = Context() // Creando el objeto que se le pasará al template
        context.setVariable("clientes", customers)

        // Renderizando el template "clientes.html" y pasandole el contexto con los atributos
        val html = templateEngine.process("clientes", context)

        val renderer = ITextRenderer().apply {
            setDocumentFromString(html)
            layout()
        }

        val outputStream = ByteArrayOutputStream()
        renderer.createPDF(outputStream)

        return outputStream.toByteArray()
    }
}