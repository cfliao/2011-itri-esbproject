package jpetstore.web.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpetstore.domain.Item;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ViewItemAction extends BaseAction
{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String itemId = request.getParameter("itemId");
        Item item = getPetStore().getItem(itemId);
        request.setAttribute("item", item);
        request.setAttribute("product", item.getProduct());
        return mapping.findForward("success");
    }

}
