package sh.alessandro.finances.api.calculator.controller

import jakarta.servlet.http.HttpServletRequest
import lombok.NoArgsConstructor
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import sh.alessandro.finances.api.calculator.domain.service.ClientService
import sh.alessandro.finances.api.calculator.domain.service.PlanService
import sh.alessandro.finances.api.calculator.dto.request.EntryDataDto
import sh.alessandro.finances.api.calculator.dto.response.ShowPlanDto
import sh.alessandro.finances.api.security.domain.models.User
import java.util.*

@RestController
@RequestMapping("api/v1/plans")
@NoArgsConstructor
class PlansController(
    private val planService: PlanService,
    private val clientService: ClientService
) {
    @GetMapping()
    fun getAllPlans(request: HttpServletRequest): ResponseEntity<Any> {
        return try {
            val client =
                clientService.findByUser(SecurityContextHolder.getContext().authentication.principal as User)
                    ?: throw NotFoundException()

            val plans =
                client.plans
                    .sortedBy { it.createdAt }
                    .map { plan -> ShowPlanDto(plan) }
            ResponseEntity(plans, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        } catch (e: NotFoundException) {
            ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED)
        }
    }

    @GetMapping("/{id}")
    fun getPlan(@PathVariable id: UUID): ResponseEntity<Map<String, ShowPlanDto>> {
        return try {
            val plan = planService.getOne(id)
            ResponseEntity(
                mapOf("plan" to ShowPlanDto(plan)), HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping()
    fun createPlan(request: HttpServletRequest, @RequestBody payload: EntryDataDto): ResponseEntity<Any> {
        return try {
            val client =
                clientService.findByUser(SecurityContextHolder.getContext().authentication.principal as User)
                    ?: throw NotFoundException()

            val plan = planService.saveOne(payload, client)

            ResponseEntity(mapOf("plan" to plan), HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(mapOf("error" to e), HttpStatus.UNPROCESSABLE_ENTITY)
        } catch (e: NotFoundException) {
            ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED)
        }
    }

    @DeleteMapping("/{id}")
    fun deletePlan(@PathVariable id: UUID): ResponseEntity<Any> {
        return try {
            planService.deleteOne(id)
            ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }
}
