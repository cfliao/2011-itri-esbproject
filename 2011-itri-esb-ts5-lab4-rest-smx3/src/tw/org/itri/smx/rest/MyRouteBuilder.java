package tw.org.itri.smx.rest;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder
{

    @Override
    public void configure() throws Exception
    {
        
        from("jbi:service:http://www.itri.org.tw/smx/camelPipeline")
        .bean(new RestBean())
        .to("jbi:service:http://www.itri.org.tw/smx/filesender");
       
    }

}
