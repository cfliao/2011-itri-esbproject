package tw.org.itri.smx.ws;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class PasswordCallback implements CallbackHandler
{

    private static Map<String, String> passwords = new HashMap<String, String>();

    static
    {
        passwords.put("clientkey", "itri2011");
        passwords.put("serverkey", "itri2011");
    }

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException
    {
        for (int i = 0; i < callbacks.length; i++)
        {
            WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];

            String pass = (String) passwords.get(pc.getIdentifer());
            if (pass != null)
            {
                pc.setPassword(pass);
            }
        }
    }

}
