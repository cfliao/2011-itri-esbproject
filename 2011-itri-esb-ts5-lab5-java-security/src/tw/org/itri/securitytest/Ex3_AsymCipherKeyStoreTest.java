/**
 * (2011) National Taiwan University, Department of Computer Science and Information Engineering
 */
package tw.org.itri.securitytest;

import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;

import javax.crypto.Cipher;

/**
 * @author Chun-Feng Liao (2011/2/24)
 *
 */
public class Ex3_AsymCipherKeyStoreTest
{

    private final static String mySecret = "this is an apple.";

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        Cipher cipherEngine = Cipher.getInstance("RSA");

        // read key from keystore
        InputStream is = Ex2_SymmetricCipherKeyStoreTest.class.getClassLoader().getResourceAsStream("asym.jks");
        KeyStore keystore = KeyStore.getInstance("JKS");
        keystore.load(is, "itri2011".toCharArray());
        Key privateKey = keystore.getKey("itrikey", "itri2011".toCharArray());
        System.out.println("Private Key: " + ByteTransfer.toHex(privateKey.getEncoded()));
        System.out.println("");

        Certificate publicKey = keystore.getCertificate("itrikey");

        // Start Encryption

        cipherEngine.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] encryptedSeret = cipherEngine.doFinal(mySecret.getBytes());

        System.out.println("before encryption: " + mySecret);

        System.out.println("before encryption (Hex): " + ByteTransfer.toHex(mySecret.getBytes()));

        System.out.println("after encryption: " + ByteTransfer.toHex(encryptedSeret));

        System.out.println("");

        // Start Decryption

        cipherEngine.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedSeret = cipherEngine.doFinal(encryptedSeret);

        System.out.println("after decryption (Hex): " + ByteTransfer.toHex(decryptedSeret));

        System.out.println("after decryption: " + new String(decryptedSeret));

    }

}
