import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "echo_user")
@Entity
class EchoUserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var nickname: String,
    var age: Int,
    var createdDt: LocalDateTime = LocalDateTime.now()
) {
    var updatedDt: LocalDateTime? = null
}
