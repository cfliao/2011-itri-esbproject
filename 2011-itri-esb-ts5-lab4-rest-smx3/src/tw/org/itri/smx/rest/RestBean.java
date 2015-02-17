package tw.org.itri.smx.rest;

import java.io.IOException;
import java.io.StringReader;

import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class RestBean
{
    private final static Logger logger = Logger.getLogger(RestBean.class);

    private final SAXBuilder builder = new SAXBuilder();

    private final XMLOutputter outputter = new XMLOutputter();

    @Handler
    public String process(@Body String body)
    {

        logger.info("body: " + body);

        Document xmlDocument = null;

        try
        {
            xmlDocument = builder.build(new StringReader(body));
        }
        catch (JDOMException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        /*
         * <rest url="http://localhost:8192" path="/adder/add/1/2" />
         */
        Element restElement = xmlDocument.getRootElement();
        String url = restElement.getAttributeValue("url");
        String path = restElement.getAttributeValue("path");
        
        logger.info("url=" +url);
        logger.info("path=" + path);
        logger.info("Triggering REST call");

        WebClient client = WebClient.create(url);
        Answer result = client.path(path).accept("application/xml").get(Answer.class);
        
        logger.info("The result is " + result.getResult());
        
        Element restResult = new Element("rest");
        restResult.setAttribute("result", Integer.toString(result.getResult()));

        return outputter.outputString(restResult);
    }
}
