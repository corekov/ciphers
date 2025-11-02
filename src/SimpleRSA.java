import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class SimpleRSA {

    private BigInteger n;
    private BigInteger e;
    private BigInteger d;
    private static final int BIT_LENGTH = 1024;

    // generating keys in the constructor
    public SimpleRSA() {
        generateKeys();
    }

    private void generateKeys() {
        SecureRandom random = new SecureRandom();

        // choose two prime different numbers
        BigInteger p = BigInteger.probablePrime(BIT_LENGTH / 2, random);
        BigInteger q = BigInteger.probablePrime(BIT_LENGTH / 2, random);

        // n = p * q
        n = p.multiply(q);

        // φ(n) = (p-1)(q-1)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // We choose e, which is coprime with φ(n), usually 65537
        e = BigInteger.valueOf(65537);

        // GCD(e, φ(n)) = 1
        if (!phi.gcd(e).equals(BigInteger.ONE)) {
            // if it's suddenly not mutually prime, we'll choose another one
            e = BigInteger.valueOf(3);
            while (!phi.gcd(e).equals(BigInteger.ONE)) {
                e = e.add(BigInteger.TWO);
            }
        }

        // e*d ≡ 1 (mod φ(n)) - d is found using the extended Euclidean algorithm
        d = e.modInverse(phi);
    }

    // c = m^e mod n
    // c - ciphertext
    public String encrypt(String message) {
        BigInteger m = new BigInteger(1, message.getBytes(StandardCharsets.UTF_8));
        BigInteger c = m.modPow(e, n);
        return c.toString(16); // returning the hex string
    }

    // m = c^d mod n
    public String decrypt(String encryptedHex) {
        BigInteger c = new BigInteger(encryptedHex, 16);
        BigInteger m = c.modPow(d, n);
        return new String(m.toByteArray(), StandardCharsets.UTF_8);
    }
}
