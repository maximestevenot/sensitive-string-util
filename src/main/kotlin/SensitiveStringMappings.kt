import SensitiveString.Companion.DEFAULT_CHARSET
import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.Charset

fun String.toSensitiveString() = SensitiveString(toCharArray())

fun ByteArray.toSensitiveString(charset: Charset = DEFAULT_CHARSET) = ByteBuffer.wrap(this).toSensitiveString(charset)

fun ByteBuffer.toSensitiveString(charset: Charset = DEFAULT_CHARSET) = SensitiveString(charset.decode(this).array())

fun CharArray.toSensitiveString() = SensitiveString(this)

fun CharBuffer.toSensitiveString() = SensitiveString(array())

fun SensitiveString.toByteArray(charset: Charset = DEFAULT_CHARSET): ByteArray = charset.encode(CharBuffer.wrap(data)).array()