import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8

data class SensitiveString(val data: CharArray) {

    companion object {
        val DEFAULT_CHARSET: Charset = UTF_8
    }

    fun clear() = data.fill('0')

    fun equals(other: String): Boolean {
        return this == other.toSensitiveString()
    }

    fun equals(other: CharArray): Boolean {
        return this == SensitiveString(other)
    }

    fun equals(other: ByteArray, charset: Charset = DEFAULT_CHARSET): Boolean {
        return this == other.toSensitiveString(charset)
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


}
