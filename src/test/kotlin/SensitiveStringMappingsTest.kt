import SensitiveString.Companion.DEFAULT_CHARSET
import org.junit.jupiter.api.Test
import java.nio.ByteBuffer
import java.nio.CharBuffer

internal class SensitiveStringMappingsTest {

    @Test
    fun `String toSensitiveString should succeed`() {
        val inputString = "12345"
        val secret = inputString.toSensitiveString()
        assert(secret.data.contentEquals(inputString.toCharArray()))
    }

    @Test
    fun `ByteArray toSensitiveString should succeed`() {
        val secret = "123".toByteArray().toSensitiveString()
        assert(secret.data.contentEquals("123".toCharArray()))
    }

    @Test
    fun `ByteBuffer toSensitiveString should succeed`() {
        val secret = ByteBuffer.wrap("123".toByteArray()).toSensitiveString()
        assert(secret.data.contentEquals("123".toCharArray()))
    }

    @Test
    fun `CharBuffer toSensitiveString should succeed`() {
        val secret = CharBuffer.wrap("123".toCharArray()).toSensitiveString()
        assert(secret.data.contentEquals("123".toCharArray()))
    }

    @Test
    fun `empty string toSensitiveString should succeed`() {
        val secret = "".toSensitiveString()
        assert(secret.data.isEmpty())
    }


    @Test
    fun `sensitiveString toByteArray should succeed`() {
        val byteArray = "123".toSensitiveString().toByteArray()
        val expected = DEFAULT_CHARSET.encode(CharBuffer.wrap("123")).array()
        assert(byteArray.contentEquals(expected))
    }

    @Test
    fun `clear should put 0 in data`() {
        val secret = "1234".toSensitiveString()
        secret.clear()
        assert(secret.data.contentEquals("0000".toCharArray()))
    }

    @Test
    fun `equals to string should succeed`() {
        val secret = "1234".toSensitiveString()
        assert(secret.equals("1234"))
        assert(!secret.equals("ABCD"))
    }

    @Test
    fun `equals to char array should succeed`() {
        val secret = "1234".toSensitiveString()
        assert(secret.equals("1234".toCharArray()))
        assert(!secret.equals("ABCD".toCharArray()))
    }

    @Test
    fun `equals to byte array should succeed`() {
        val secret = "1234".toSensitiveString()
        assert(secret.equals("1234".toByteArray()))
        assert(!secret.equals("ABCD".toByteArray()))
    }

    @Test
    fun `to string should print asterisk but preserve data`() {
        val secret = "1234".toSensitiveString()
        assert(secret.toString() == "****")
        assert(secret.data.contentEquals("1234".toCharArray()))
    }

}
