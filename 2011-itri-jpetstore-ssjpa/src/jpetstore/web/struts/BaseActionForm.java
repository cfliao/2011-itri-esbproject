package jpetstore.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BaseActionForm extends ActionForm
{

    private static final long serialVersionUID = -4403887959362296526L;

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
        ActionErrors actionErrors = null;
        List<String> errorList = new ArrayList<String>();
        doValidate(mapping, request, errorList);
        request.setAttribute("errors", errorList);
        if (!errorList.isEmpty())
        {
            actionErrors = new ActionErrors();
            actionErrors.add(ActionErrors.GLOBAL_ERROR, new ActionError("global.error"));
        }
        return actionErrors;
    }

    public void doValidate(ActionMapping mapping, HttpServletRequest request, List<String> errors)
    {
    }

    /* Protected Methods */

    protected void addErrorIfStringEmpty(List<String> errors, String message, String value)
    {
        if (value == null || value.trim().length() < 1)
        {
            errors.add(message);
        }
    }

}
