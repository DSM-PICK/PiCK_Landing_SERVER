package pick_landing_server.domain.application.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Application(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name : String,
    val schoolNum : Int,
    val phone : String,
    val major : Major,
    val motivation : String
)
