package com.abrahamxts.kotlinbank.controllers

import com.abrahamxts.kotlinbank.dto.CustomerDTO
import com.abrahamxts.kotlinbank.useCases.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
@CrossOrigin(
    origins = ["*"],
    methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE]
)
class CustomerController(
    private val createCustomer: CreateCustomer,
    private val generateReport: GenerateReport,
    private val deleteCustomer: DeleteCustomer
) {
    @GetMapping("/{clientNumber}")
    fun getCustomer(@PathVariable clientNumber: String): ResponseEntity<CustomerDTO> {
        val customer = this.generateReport.getCustomer(clientNumber)
        return ResponseEntity(customer, HttpStatus.OK)
    }

    @GetMapping("")
    fun getAllCustomers(): ResponseEntity<List<CustomerDTO>> {
        val customers = this.generateReport.getAllCustomers()
        return ResponseEntity<List<CustomerDTO>>(customers, HttpStatus.OK)
    }

    data class AddCustomerBody(val curp: String)

    @PostMapping("add")
    fun addCustomer(@RequestBody addCustomerBody: AddCustomerBody): ResponseEntity<CustomerDTO> {
        val customer = this.createCustomer.run(addCustomerBody.curp)
        return ResponseEntity<CustomerDTO>(customer, HttpStatus.CREATED)
    }

    data class DeleteCustomerBody(val clientNumber: String)

    @DeleteMapping("delete")
    fun deleteCustomer(@RequestBody deleteCustomerBody: DeleteCustomerBody): ResponseEntity<String> {
        val message = this.deleteCustomer.run(deleteCustomerBody.clientNumber)
        return ResponseEntity<String>(message, HttpStatus.OK)
    }

    @GetMapping("report")
    fun generateReport(): ResponseEntity<ByteArray> {

        val pdf = generateReport.run()

        val headers = HttpHeaders()
        headers.contentLength = pdf.size.toLong()
        headers.contentType = MediaType.APPLICATION_PDF
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clientes.pdf")

        return ResponseEntity<ByteArray>(pdf, headers, HttpStatus.OK)
    }
}