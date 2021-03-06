package jpetstore.web.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpetstore.domain.Account;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class NewAccountFormAction extends BaseAction {

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    AccountActionForm workingAcctForm = new AccountActionForm();
    request.getSession().removeAttribute("workingAccountForm");
    request.getSession().setAttribute("workingAccountForm", workingAcctForm);
    if (workingAcctForm.getAccount() == null) {
      workingAcctForm.setAccount(new Account());
    }
    if (workingAcctForm.getCategories() == null) {
      workingAcctForm.setCategories(getPetStore().getCategoryList());
    }
    return mapping.findForward("success");
  }

}
