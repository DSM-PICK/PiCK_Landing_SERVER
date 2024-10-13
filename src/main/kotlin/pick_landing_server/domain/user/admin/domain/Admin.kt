package pick_landing_server.domain.user.admin.domain

import jakarta.persistence.*

@Entity
data class Admin(
    @Id
    @GeneratedValue
    private var id: Long? = null,
    @Column
    private var accountId: String,
    @Column
    private var password: String
)
