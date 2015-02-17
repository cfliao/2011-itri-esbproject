package tw.org.itri.smx.rest;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

public class CalculatorService
{
    public static void main(String[] args)
    {
        new CalculatorService();
    }

    public CalculatorService()
    {
        JAXRSServerFactoryBean restServer = new JAXRSServerFactoryBean();
        restServer.setResourceClasses(Answer.class);
        restServer.setServiceBeans(new CalculatorBean());
        restServer.setAddress("http://localhost:8192/");
        restServer.create();
    }
}
