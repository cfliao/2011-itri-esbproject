package tw.org.itri.smx.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

/**
 * @author Chun-Feng Liao (2010/4/15)
 */
public class HttpClientExample1
{

    private static String url = "http://localhost:8192/adder";

    public static void main(String[] args) throws UnsupportedEncodingException
    {
        // Create an instance of HttpClient.
        HttpClient client = new HttpClient();

        // Create a method instance.
        // GetMethod method = new GetMethod(url);
        PostMethod post = new PostMethod(url);
        RequestEntity entity = new StringRequestEntity("<abc>abc</abc>", "text/xml", "ISO-8859-1");
        post.setRequestEntity(entity);

        // Provide custom retry handler is necessary

        try
        {
            client.executeMethod(post);

            byte[] responseBody = post.getResponseBody();

            System.out.println(new String(responseBody, "UTF-8"));

        }
        catch (HttpException e)
        {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            post.releaseConnection();
        }
    }

    public static InputStream string2InputStream(String str) throws UnsupportedEncodingException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        return bais;
    }

}
