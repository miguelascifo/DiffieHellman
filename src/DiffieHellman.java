import java.math.BigInteger;

public class DiffieHellman {

    public static void main(String[] argv) {

        BigInteger clavePrivada = new BigInteger(String.valueOf(497));
        BigInteger p = new BigInteger(String.valueOf(852));
        BigInteger n = new BigInteger(String.valueOf(749720981));
        BigInteger Z_Bob = new BigInteger(String.valueOf(445683894));

        System.out.println("Ejercicio 2\n");
        ataque(exponencialModular(p, clavePrivada, n), Z_Bob, p, n);

        System.out.println("\n\nEjercicio 3 - Fuerza Bruta\n");
        ataque(new BigInteger(String.valueOf(79525)), new BigInteger(String.valueOf(106243)), new BigInteger(String.valueOf(253)), new BigInteger(String.valueOf(131074)));
    }

    private static BigInteger exponencialModular(BigInteger p, BigInteger clavePrivada, BigInteger n) {
        BigInteger resultado = BigInteger.ONE;
        p = p.mod(n);

        for (int i = 0; i < clavePrivada.bitLength(); ++i) {
            if (clavePrivada.testBit(i)) {
                resultado = resultado.multiply(p).mod(n);
            }
            p = p.multiply(p).mod(n);
        }
        return resultado;
    }

    private static void ataque(BigInteger A, BigInteger B, BigInteger b, BigInteger m) {
        int clave_alice = 0;
        int clave_bob = 0;

        for (int i = 0; i <= m.intValue() - 1; i++) {
            if (A.equals(exponencialModular(b, new BigInteger(String.valueOf(i)), m))) {
                System.out.println("Clave secreta de Alice: " + i);
                System.out.println("Z de Alice: " + exponencialModular(b, new BigInteger(String.valueOf(i)), m));
                clave_alice = i;
                break;
            }
        }

        for (int i = 0; i <= m.intValue() - 1; i++) {
            if (B.equals(exponencialModular(b, new BigInteger(String.valueOf(i)), m))) {
                System.out.println("Clave secreta de Bob: " + i);
                System.out.println("Z de Bob: " + exponencialModular(b, new BigInteger(String.valueOf(i)), m));
                clave_bob = i;
                break;
            }
        }

        System.out.println("\nClave común para Bob: " + exponencialModular(A, new BigInteger(String.valueOf(clave_bob)), m));
        System.out.println("Clave común para Alice: " + exponencialModular(B, new BigInteger(String.valueOf(clave_alice)), m));
    }
}
