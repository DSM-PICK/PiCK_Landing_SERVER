package pick_landing_server.domain.user.admin.domain.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pick_landing_server.domain.user.admin.domain.Admin

@Repository
interface AdminRepository : CrudRepository<Admin,Long> {
    fun findByAccountId(accountId:String)
}
