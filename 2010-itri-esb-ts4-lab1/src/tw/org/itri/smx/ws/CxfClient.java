package tw.org.itri.smx.ws;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

public class CxfClient
{

    public static void main(String[] args) throws MalformedURLException
    {
        URL wsdlLocation = new URL("http://localhost:8192/Adder?wsdl");
        QName serviceName = new QName("http://ws.smx.itri.org.tw/", "CalculatorImplService");

        Service service = Service.create(wsdlLocation, serviceName);
        
        Calculator adder = service.getPort(Calculator.class);
        
        // add logger
        Client client = ClientProxy.getClient(adder);
        client.getEndpoint().getInInterceptors().add(new LoggingInInterceptor());
        client.getEndpoint().getOutInterceptors().add(new LoggingOutInterceptor());
        
        System.out.println(adder.add(1, 1));

    }

}
