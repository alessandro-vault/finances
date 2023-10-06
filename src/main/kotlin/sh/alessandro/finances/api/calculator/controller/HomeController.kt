package sh.alessandro.finances.api.calculator.controller

import org.springframework.web.bind.annotation.*

@RestController
class HomeController {
    @GetMapping("/")
    fun home(): String {
        return "Hello World!"
    }
}