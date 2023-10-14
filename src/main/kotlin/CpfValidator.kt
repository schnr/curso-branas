class CpfValidator {

  companion object {
    private const val ZERO = 0
    private const val ONE = 1
    private const val TWO = 2
    private const val TEN = 10
    private const val ELEVEN = 11
  }

  fun validate(cpfInput: String): Boolean {
    if (cpfInput.isEmpty()) return false
    val cpf = cpfInput.filter { it.isDigit() }
    if (isInvalidLength(cpf)) return false
    if (allDigitsTheSame(cpf)) return false
    val dg1 = calculateDigit(cpf, TEN)
    val dg2 = calculateDigit(cpf, ELEVEN)
    val checkDigit = extractDigit(cpf)
    val calculateDigit = "$dg1$dg2"
    return checkDigit == calculateDigit
  }

  private fun isInvalidLength(cpf: String) = cpf.length != ELEVEN

  private fun allDigitsTheSame(cpf: String) = cpf.all { c -> c == cpf[ZERO] }

  private fun calculateDigit(cpf: String, factorInput: Int): Int {
    var total = ZERO
    var factor = factorInput
    for (digit in cpf) {
      if (factor > ONE) {
        total += digit.digitToInt() * factor
        factor = factor.dec()
      }
    }
    val rest = total.rem(ELEVEN)
    return if (rest < TWO) ZERO else ELEVEN - rest
  }

  private fun extractDigit(cpf: String) = cpf.substring(cpf.length - TWO, cpf.length)
}
