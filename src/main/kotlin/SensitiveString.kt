class SensitiveString(
    val data: CharArray
) {

    companion object {
        fun fromString(inputString: String): SensitiveString {
            return SensitiveString(inputString.toCharArray())
        }
    }

    fun clear() {
        data.fill('0')
    }

    fun equals(other: String): Boolean {
        return this == fromString(other)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SensitiveString

        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }

    override fun toString(): String {
        return data.concatToString()
    }
}
