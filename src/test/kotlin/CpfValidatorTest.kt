import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

class CpfValidatorTest {

  private val cpfValidator = CpfValidator()

  @TestFactory
  fun `test valid and invalid cpf`() =
    listOf(
      "673.393.280-68" to true,
      "376.111.000-68" to true,
      "246.104.130-00" to true,
      "95818705552" to true,
      "111.111.111-11" to false,
      "376.111.000" to false,
      "246.104.131-00" to false,
      "abcd" to false
    ).map { (cpf, expected) ->
        dynamicTest("given $cpf when validate then return ${if (expected) "valid" else "invalid"}") {
          val result = cpfValidator.validate(cpf)
          assertEquals(expected, result)
        }
      }
}