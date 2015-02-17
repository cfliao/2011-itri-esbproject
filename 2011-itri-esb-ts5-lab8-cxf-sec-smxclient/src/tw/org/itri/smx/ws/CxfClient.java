package tw.org.itri.smx.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.binding.soap.saaj.SAAJOutInterceptor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;

public class CxfClient
{

    public static void main(String[] args) throws MalformedURLException
    {
        URL wsdlLocation = new URL("http://localhost:8192/Adder?wsdl");
        QName serviceName = new QName("http://ws.smx.itri.org.tw/", "CalculatorImplService");

        Service service = Service.create(wsdlLocation, serviceName);
        
        Calculator adder = service.getPort(Calculator.class);
        
        Client client = ClientProxy.getClient(adder);
        Endpoint endpoint = client.getEndpoint();
        //
        
        endpoint.getInInterceptors().add(new LoggingInInterceptor());
        endpoint.getInInterceptors().add(new SAAJInInterceptor());
        endpoint.getInInterceptors().add(new WSS4JInInterceptor(getWSS4JProperties()));
        
        endpoint.getOutInterceptors().add(new LoggingOutInterceptor());
        endpoint.getOutInterceptors().add(new SAAJOutInterceptor());
        endpoint.getOutInterceptors().add(new WSS4JOutInterceptor(getWSS4JProperties()));
        
        System.out.println(adder.add(1, 1));

    }

    private static Map<String, Object> getWSS4JProperties()
    {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put("action", "Signature Encrypt");
        props.put("user", "clientkey");
        props.put("signaturePropFile", "client.crypto.properties");
        props.put("encryptionPropFile", "client.crypto.properties");
        props.put("decryptionPropFile", "client.crypto.properties");
        props.put("encryptionUser", "serverkey");
        props.put("passwordCallbackClass", "tw.org.itri.smx.ws.PasswordCallback");

        return props;
    }
}
