package tw.org.itri.smx.ws;

import javax.xml.ws.Endpoint;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;

public class Server
{

    public Server()
    {
        System.out.println("Starting server");
        String address = "http://localhost:8192/Adder";
        EndpointImpl ep = (EndpointImpl) Endpoint.publish(address, new CalculatorImpl());

        // add logger
        ep.getServer().getEndpoint().getInInterceptors().add(new LoggingInInterceptor());
        ep.getServer().getEndpoint().getOutInterceptors().add(new LoggingOutInterceptor());

    }

    public static void main(String[] args)
    {
        new Server();
    }

}
