package sh.alessandro.finances.api.calculator.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sh.alessandro.finances.api.calculator.dto.EntryDataDto

@RestController
@RequestMapping("api/v1/plans")
class PlansController {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun createPlan(@RequestBody entryDataDto: EntryDataDto): ResponseEntity<String> {

        println(entryDataDto)
        return ResponseEntity("Plan created", HttpStatus.CREATED)
    }
}