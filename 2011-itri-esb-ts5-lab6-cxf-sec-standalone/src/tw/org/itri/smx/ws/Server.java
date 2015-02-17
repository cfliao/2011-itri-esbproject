package tw.org.itri.smx.ws;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;

import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.binding.soap.saaj.SAAJOutInterceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;

public class Server
{

    public Server()
    {
        System.out.println("Starting server");
        String address = "http://localhost:8192/Adder";
        EndpointImpl ep = (EndpointImpl) Endpoint.publish(address, new CalculatorImpl());

        ep.getServer().getEndpoint().getInInterceptors().add(new LoggingInInterceptor());
        ep.getServer().getEndpoint().getInInterceptors().add(new SAAJInInterceptor());
        ep.getServer().getEndpoint().getInInterceptors().add(new WSS4JInInterceptor(getWSS4JProperties()));
        //
        ep.getServer().getEndpoint().getOutInterceptors().add(new SAAJOutInterceptor());
        ep.getServer().getEndpoint().getOutInterceptors().add(new WSS4JOutInterceptor(getWSS4JProperties()));
        ep.getServer().getEndpoint().getOutInterceptors().add(new LoggingOutInterceptor());

    }

    public static void main(String[] args)
    {
        new Server();
    }

    private Map<String, Object> getWSS4JProperties()
    {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put("action", "Signature Encrypt");
        props.put("user", "serverkey");
        props.put("signaturePropFile", "server.crypto.properties");
        props.put("encryptionPropFile", "server.crypto.properties");
        props.put("decryptionPropFile", "server.crypto.properties");
        props.put("encryptionUser", "clientkey");
        props.put("passwordCallbackClass", "tw.org.itri.smx.ws.PasswordCallback");

        return props;
    }
}
