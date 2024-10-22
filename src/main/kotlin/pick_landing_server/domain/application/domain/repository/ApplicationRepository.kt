package pick_landing_server.domain.application.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pick_landing_server.domain.application.domain.Application

@Repository
interface ApplicationRepository : JpaRepository<Application, Long>{
}
