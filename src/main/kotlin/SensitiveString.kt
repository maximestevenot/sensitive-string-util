import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class SensitiveString(
    val data: CharArray
) {

    companion object {
        val DEFAULT_CHARSET: Charset = StandardCharsets.UTF_8

        fun fromString(inputString: String): SensitiveString = SensitiveString(inputString.toCharArray())

        fun fromByteArray(inputByteArray: ByteArray, charset: Charset = DEFAULT_CHARSET): SensitiveString =
            fromByteBuffer(ByteBuffer.wrap(inputByteArray), charset)

        fun fromByteBuffer(inputByteBuffer: ByteBuffer, charset: Charset = DEFAULT_CHARSET): SensitiveString =
            SensitiveString(charset.decode(inputByteBuffer).array())

        fun fromCharBuffer(inputCharBuffer: CharBuffer): SensitiveString = SensitiveString(inputCharBuffer.array())
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

fun CharBuffer.toSensitiveString() = SensitiveString.fromCharBuffer(this)

fun CharArray.toSensitiveString() = SensitiveString(this)

fun String.toSensitiveString() = SensitiveString.fromString(this)

fun ByteBuffer.toSensitiveString(charset: Charset = SensitiveString.DEFAULT_CHARSET) =
    SensitiveString.fromByteBuffer(this, charset)

fun ByteArray.toSensitiveString(charset: Charset = SensitiveString.DEFAULT_CHARSET) =
    SensitiveString.fromByteArray(this, charset)
