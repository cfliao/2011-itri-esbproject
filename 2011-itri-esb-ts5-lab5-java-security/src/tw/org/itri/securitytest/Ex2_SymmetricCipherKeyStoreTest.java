/**
 * (2011) National Taiwan University, Department of Computer Science and Information Engineering
 */
package tw.org.itri.securitytest;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *  keytool -genseckey -keyalg desede -storetype JCEKS -keystore key.jceks
 *  keytool -list -storetype JCEKS -keystore key.jceks  
 */
public class Ex2_SymmetricCipherKeyStoreTest
{
    private final static String mySecret = "this is an apple.";

    public static void main(String[] args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher cipherEngine = Cipher.getInstance("DESede");

        // read key from keystore
        InputStream is = Ex2_SymmetricCipherKeyStoreTest.class.getClassLoader().getResourceAsStream("sym.jceks");
        KeyStore keystore = KeyStore.getInstance("JCEKS");
        keystore.load(is, "itri2011".toCharArray());
        Key secretKey = keystore.getKey("mykey", "itri2011".toCharArray());
        System.out.println("Secret Key: " + ByteTransfer.toHex(secretKey.getEncoded()));
        System.out.println("");

        // Start Encryption

        cipherEngine.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedSeret = cipherEngine.doFinal(mySecret.getBytes());

        System.out.println("before encryption: " + mySecret);

        System.out.println("before encryption (Hex): " + ByteTransfer.toHex(mySecret.getBytes()));

        System.out.println("after encryption: " + ByteTransfer.toHex(encryptedSeret));

        System.out.println("");

        // Start Decryption

        cipherEngine.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedSeret = cipherEngine.doFinal(encryptedSeret);

        System.out.println("after decryption (Hex): " + ByteTransfer.toHex(decryptedSeret));

        System.out.println("after decryption: " + new String(decryptedSeret));

    }

}
