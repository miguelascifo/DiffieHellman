import java.math.BigInteger;

public class DiffieHellman {

    private static BigInteger clavePrivada = new BigInteger(String.valueOf(497));

    // Me pongo de acuerdo con estos valores con mi pareja
    private static BigInteger p = new BigInteger(String.valueOf(852));
    private static BigInteger n = new BigInteger(String.valueOf(749720981));

    public static void main(String[] argv) {

        System.out.println("Ejercicio 2\n");
        fuerzaBruta(exponencialModular(p, clavePrivada, n), new BigInteger(String.valueOf(445683894)), p, n);

        System.out.println("\n\nEjercicio 3 - Fuerza Bruta\n");
        fuerzaBruta(new BigInteger(String.valueOf(79525)), new BigInteger(String.valueOf(106243)), new BigInteger(String.valueOf(253)), new BigInteger(String.valueOf(131074)));
    }

    private static BigInteger exponencialModular(BigInteger base, BigInteger exp, BigInteger mod) {
        BigInteger result = BigInteger.ONE;
        base = base.mod(mod);

        for (int idx = 0; idx < exp.bitLength(); ++idx) {
            if (exp.testBit(idx)) {
                result = result.multiply(base).mod(mod);
            }
            base = base.multiply(base).mod(mod);
        }
        return result;
    }

    private static void fuerzaBruta(BigInteger A, BigInteger B, BigInteger b, BigInteger m) {

        int i, j;
        int clave_alice = 0;
        int clave_bob = 0;
        boolean bob = false;
        boolean alice = false;

        for (i = 0; i <= m.intValue() - 1; i++) {

            if (B.equals(exponencialModular(b, new BigInteger(String.valueOf(i)), m))) {
                // Ya tengo el numero secreto de Bob
                System.out.println("Clave secreta de Bob: " + i);
                System.out.println("Z de Bob: " + exponencialModular(b, new BigInteger(String.valueOf(i)), m));
                bob = true;
                clave_bob = i;
                break;
            }

            if (A.equals(exponencialModular(b, new BigInteger(String.valueOf(i)), m))) {
                // Ya tengo el numero secreto de Alice
                System.out.println("Clave secreta de Alice: " + i);
                System.out.println("Z de Alice: " + exponencialModular(b, new BigInteger(String.valueOf(i)), m));
                alice = true;
                clave_alice = i;
                break;
            }
        }

        if (bob) {
            for (j = i; j <= m.intValue() - 1; j++) {
                if (A.equals(exponencialModular(b, new BigInteger(String.valueOf(j)), m))) {
                    System.out.println("Clave secreta de Alice: " + j);
                    System.out.println("Z de Alice: " + exponencialModular(b, new BigInteger(String.valueOf(j)), m));
                    clave_alice = j;
                    break;
                }
            }
        }

        if (alice) {
            for (j = i; j <= m.intValue() - 1; j++) {
                if (B.equals(exponencialModular(b, new BigInteger(String.valueOf(j)), m))) {
                    System.out.println("Clave secreta de Bob: " + j);
                    System.out.println("Z de Bob: " + exponencialModular(b, new BigInteger(String.valueOf(j)), m));
                    clave_bob = j;
                    break;
                }
            }
        }

        System.out.println("\nClave común para Bob: " + exponencialModular(A, new BigInteger(String.valueOf(clave_bob)), m));
        System.out.println("Clave común para Alice: " + exponencialModular(B, new BigInteger(String.valueOf(clave_alice)), m));
    }
}
