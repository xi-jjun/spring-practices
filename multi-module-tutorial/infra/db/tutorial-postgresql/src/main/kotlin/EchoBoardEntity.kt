import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "echo_board")
@Entity
class EchoBoardEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var name: String,
    var createdDt: LocalDateTime = LocalDateTime.now()
) {
    var updatedDt: LocalDateTime? = null
}
