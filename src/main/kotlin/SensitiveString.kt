import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class SensitiveString(
    val data: CharArray
) {

    companion object {
        private val DEFAULT_CHARSET = StandardCharsets.UTF_8

        fun fromString(inputString: String): SensitiveString = SensitiveString(inputString.toCharArray())

        fun fromByteArray(inputByteArray: ByteArray, charset: Charset = DEFAULT_CHARSET): SensitiveString =
            SensitiveString(charset.decode(ByteBuffer.wrap(inputByteArray)).array())
    }


    fun clear() = data.fill('0')

    fun equals(other: String): Boolean {
        return this == fromString(other)
    }

    fun equals(other: CharArray): Boolean {
        return this == SensitiveString(other)
    }

    fun equals(other: ByteArray, charset: Charset = DEFAULT_CHARSET): Boolean {
        return this == fromByteArray(other, charset)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SensitiveString

        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int = data.contentHashCode()

    override fun toString(): String = data.map { '*' }.toCharArray().concatToString()

    fun toByteArray(charset: Charset = DEFAULT_CHARSET): ByteArray = charset.encode(CharBuffer.wrap(data)).array()

}
