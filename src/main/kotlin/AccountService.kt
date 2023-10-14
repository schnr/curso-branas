import jdk.internal.org.jline.utils.Log
import java.time.LocalDateTime
import java.util.UUID

class AccountService(private val cpfValidator: CpfValidator) {

  fun singUp(input: InputData): Any {

    val connectionDb = "any"

    try {
      val accountId = UUID.randomUUID()
      val verificationCode = UUID.randomUUID()
      val date = LocalDateTime.now();
      val existingAccount = false //select db;

      if (existingAccount) throw RuntimeException("Account already exists")
      if (!input.name.matches(Regex("[a-zA-Z] [a-zA-Z]+"))) throw RuntimeException("Invalid name")
      if (!input.email.matches(Regex("^(.+)@(.+)\$"))) throw RuntimeException("Invalid email")
      if (!cpfValidator.validate(input.cpf)) throw RuntimeException("Invalid cpf")
      if (input.isDriver && !input.carPlate.matches(Regex("[A-Z]{3}[0-9]{4}"))) throw RuntimeException(
        "Invalid plate"
      )
      val saveInDb = "TODO"
      sendEmail(
        input.email,
        "Verification",
        "Please verify your code at first login $verificationCode"
      )
      return accountId

    } finally {
      Log.info(      "close DB")
    }
  }

  private fun sendEmail(email: String, subject: String, message: String) {
    Log.info("Send email: $email, $subject, $message")
  }
}

data class InputData(
  val accountId: String,
  val name: String,
  val email: String,
  val cpf: String,
  val carPlate: String,
  val isPassenger: Boolean,
  val isDriver: Boolean,
  val date: LocalDateTime,
  val isVerified: String,
  val verificationCode: String
)