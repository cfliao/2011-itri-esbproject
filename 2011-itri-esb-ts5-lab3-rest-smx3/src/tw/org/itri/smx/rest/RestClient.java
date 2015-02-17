package tw.org.itri.smx.rest;

import org.apache.cxf.jaxrs.client.WebClient;

public class RestClient
{
    public static void main(String[] args)
    {
        WebClient client = WebClient.create("http://localhost:8192");
        Answer result = client.path("/adder/add/1/2").accept("application/xml").get(Answer.class);
        System.out.println("The result is " + result.getResult());
    }
}
