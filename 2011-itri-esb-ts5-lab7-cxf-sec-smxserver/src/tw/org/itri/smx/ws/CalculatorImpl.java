package tw.org.itri.smx.ws;

import javax.jws.WebService;
 
@WebService(endpointInterface = "tw.org.itri.smx.ws.Calculator")
public class CalculatorImpl implements Calculator
{
    @Override
    public int add(int i, int j)
    {
        return i + j;
    }

}
