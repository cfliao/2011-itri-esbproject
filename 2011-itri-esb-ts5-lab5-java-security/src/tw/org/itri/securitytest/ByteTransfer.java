/**
 * (2011) National Taiwan University, Department of Computer Science and Information Engineering
 */
package tw.org.itri.securitytest;

/**
 * @author Chun-Feng Liao (2011/2/24)
 *
 */
public class ByteTransfer
{
    // public static String byteArrayToHex(byte[] input)
    // {
    // BigInteger bi = new BigInteger(input);
    // return bi.toString(16);
    // }

    private final static char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static String toHex(byte[] bytes)
    {
        StringBuilder buffer;

        buffer = new StringBuilder();
        for (byte b : bytes)
        {
            buffer.append(HEX[(b >> 4) & 0xf]);
            buffer.append(HEX[b & 0xf]);
        }
        return buffer.toString();
    }

    public static byte[] fromHex(String hex) throws NumberFormatException
    {
        char[] chars;
        char c;
        int i;
        int j;
        byte[] bytes;
        byte b;

        chars = hex.toUpperCase().toCharArray();

        if (chars.length % 2 != 0)
        {
            throw new NumberFormatException("Incomplete hex value");
        }

        bytes = new byte[chars.length / 2];
        b = 0;
        j = 0;
        for (i = 0; i < chars.length; i++)
        {
            c = chars[i];
            if (c >= '0' && c <= '9')
            {
                b = (byte) ((b << 4) | (0xff & (c - '0')));
            } else if (c >= 'A' && c <= 'F')
            {
                b = (byte) ((b << 4) | (0xff & (c - 'A' + 10)));
            } else
            {
                throw new NumberFormatException("Invalid hex character: " + c);
            }
            if ((i + 1) % 2 == 0)
            {
                bytes[j++] = b;
                b = 0;
            }
        }

        return bytes;
    }

}
