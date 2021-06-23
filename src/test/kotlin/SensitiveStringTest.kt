import java.nio.ByteBuffer
import java.nio.CharBuffer

internal class SensitiveStringTest {

    @org.junit.jupiter.api.Test
    fun `from string should work`() {
        val inputString = "12345"
        val secret = SensitiveString.fromString(inputString)

        assert(secret.data.contentEquals(inputString.toCharArray()))
    }

    @org.junit.jupiter.api.Test
    fun `from byte array should work`() {
        val bytes = "123".toByteArray()
        val secret = SensitiveString.fromByteArray(bytes)

        assert(secret.data.contentEquals("123".toCharArray()))
    }

    @org.junit.jupiter.api.Test
    fun `from byte buffer should work`() {
        val bytes = "123".toByteArray()
        val secret = SensitiveString.fromByteBuffer(ByteBuffer.wrap(bytes))

        assert(secret.data.contentEquals("123".toCharArray()))
    }

    @org.junit.jupiter.api.Test
    fun `from char buffer should work`() {
        val charBuffer = CharBuffer.wrap("123".toCharArray())
        val secret = SensitiveString.fromCharBuffer(charBuffer)

        assert(secret.data.contentEquals("123".toCharArray()))
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

    @org.junit.jupiter.api.Test
    fun `equals to string should work`() {
        val secret = SensitiveString.fromString("1234")
        assert(secret.equals("1234"))
        assert(!secret.equals("ABCD"))
    }

    @org.junit.jupiter.api.Test
    fun `equals to char array should work`() {
        val secret = SensitiveString.fromString("1234")
        assert(secret.equals("1234".toCharArray()))
        assert(!secret.equals("ABCD".toCharArray()))
    }

    @org.junit.jupiter.api.Test
    fun `equals to byte array should work`() {
        val secret = SensitiveString.fromString("1234")
        assert(secret.equals("1234".toByteArray()))
        assert(!secret.equals("ABCD".toByteArray()))
    }

    @org.junit.jupiter.api.Test
    fun `to string should print asterisk but preserve data`() {
        val secret = SensitiveString.fromString("1234")
        assert(secret.toString() == "****")
        assert(secret.data.contentEquals("1234".toCharArray()))
    }


    @org.junit.jupiter.api.Test
    fun `string extension should work`() {
        assert("1234".toSensitiveString() == SensitiveString.fromString("1234"))
    }

    @org.junit.jupiter.api.Test
    fun `char array extension should work`() {
        val input = "1234".toCharArray()
        assert(input.toSensitiveString() == SensitiveString.fromString("1234"))
    }

    @org.junit.jupiter.api.Test
    fun `char buffer extension should work`() {
        val input = CharBuffer.wrap("1234".toCharArray())
        assert(input.toSensitiveString() == SensitiveString.fromString("1234"))
    }

    @org.junit.jupiter.api.Test
    fun `byte array extension should work`() {
        val input = "1234".toByteArray()
        assert(input.toSensitiveString() == SensitiveString.fromString("1234"))
    }

    @org.junit.jupiter.api.Test
    fun `byte buffer extension should work`() {
        val input = ByteBuffer.wrap("1234".toByteArray())
        assert(input.toSensitiveString() == SensitiveString.fromString("1234"))
    }

}
