package com.abrahamxts.kotlinbank.controllers

import org.springframework.http.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("health")
@CrossOrigin(
    origins = ["*"],
    methods = [RequestMethod.GET]
)
class HealthController {

    @GetMapping("")
    fun checkHealthOfService(): ResponseEntity<String> {
        return ResponseEntity<String>("Servicio funcionando correctamente", HttpStatus.OK)
    }
}