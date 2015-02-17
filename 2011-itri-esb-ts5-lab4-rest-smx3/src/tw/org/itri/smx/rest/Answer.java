package tw.org.itri.smx.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Answer
{
    private int result;

    public int getResult()
    {
        return result;
    }

    public void setResult(int result)
    {
        this.result = result;
    }

}
