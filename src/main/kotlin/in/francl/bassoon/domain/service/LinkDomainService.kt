package `in`.francl.bassoon.domain.service

class LinkDomainService {

    private val alphabet = ('a'..'z').plus('A'..'Z').plus('0'..'9')
    private val base = alphabet.size

    fun encode(n: Long): String {
        if (n == 0L) return alphabet[0].toString()
        val encoded = StringBuilder()
        var factor = n
        while (factor > 0) {
            val idx = (factor % base).toInt()
            encoded.append(alphabet[idx])
            factor /= base
        }
        return encoded.reverse().toString()
    }

    fun decode(encoded: String): Long {
        return encoded.fold(0L) { acc, char -> (acc * base) + alphabet.indexOf(char) }
    }

}
