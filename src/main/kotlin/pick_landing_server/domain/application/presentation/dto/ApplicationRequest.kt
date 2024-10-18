package pick_landing_server.domain.application.presentation.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.Length
import pick_landing_server.domain.application.domain.Major

data class ApplicationRequest (
    @field:NotBlank
    val name: String,
    @field:NotNull(message = "공백 안됨")
    val schoolNum: Int,
    @field:NotBlank(message = "phone_number은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @field:Size(min = 11, max = 11)
    val phone: String,
    @field:NotNull
    val major: Major,
    @field:NotNull
    @field:Size(max = 300, message = "300글자 내외로 작성해주세요")
    val motivation: String
)
