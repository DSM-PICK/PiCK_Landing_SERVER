package pick_landing_server.domain.user.admin.domain

import jakarta.persistence.*

@Entity
data class Admin(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var accountId: String,
    var password: String
)
