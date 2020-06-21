import java.math.BigInteger
import kotlin.random.Random

object DiffieHellman {

    fun privateKey(prime: BigInteger): BigInteger =
            BigInteger.valueOf(Random.nextLong(from = 2L, until = prime.toLong()))

    fun publicKey(p: BigInteger, g: BigInteger, privKey: BigInteger): BigInteger =
            g.modPow(privKey, p)

    fun secret(prime: BigInteger, publicKey: BigInteger, privateKey: BigInteger): BigInteger =
            publicKey.modPow(privateKey, prime)
}
