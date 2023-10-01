package sh.alessandro.finances.calculator.controller

import org.springframework.web.bind.annotation.*

@RestController
class HomeController {
    @GetMapping("/")
    fun home(): String {
        return "Hello World!"
    }
}