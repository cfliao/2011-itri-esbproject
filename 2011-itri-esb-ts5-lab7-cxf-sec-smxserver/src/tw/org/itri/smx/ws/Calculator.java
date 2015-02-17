package tw.org.itri.smx.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(use = SOAPBinding.Use.LITERAL, style = SOAPBinding.Style.DOCUMENT)
public interface Calculator
{
    @WebMethod
    public int add(int i, int j);
}
