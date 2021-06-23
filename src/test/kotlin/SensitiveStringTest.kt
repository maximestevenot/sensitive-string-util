internal class SensitiveStringTest {

    @org.junit.jupiter.api.Test
    fun `from string should work`() {
        val inputString = "12345"
        val secret = SensitiveString.fromString(inputString)

        assert(secret.data.contentEquals(inputString.toCharArray()))
    }

    @org.junit.jupiter.api.Test
    fun `from empty string should work`() {
        val inputString = ""
        val secret = SensitiveString.fromString(inputString)
        assert(secret.data.isEmpty())
    }


    @org.junit.jupiter.api.Test
    fun `clear should put 0 in data`() {
        val secret = SensitiveString.fromString("1234")
        secret.clear()
        assert(secret.data.contentEquals("0000".toCharArray()))
    }

}
