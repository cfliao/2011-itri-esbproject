/**
 * (2011) National Taiwan University, Department of Computer Science and Information Engineering
 */
package tw.org.itri.securitytest;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * @author Chun-Feng Liao (2011/2/24)
 *
 */
public class Ex1_SymmetricCipherTest
{
    private final static String mySecret = "this is an apple.";

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher cipherEngine = Cipher.getInstance("DESede");

        // Generate Key

        SecretKey secretKey = KeyGenerator.getInstance("DESede").generateKey();

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
