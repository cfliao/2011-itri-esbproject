package jpetstore.web.struts;

import javax.servlet.http.HttpServletRequest;

import jpetstore.domain.Cart;

import org.apache.struts.action.ActionMapping;

public class CartActionForm extends BaseActionForm {

  /* Private Fields */

  /**
     * 
     */
    private static final long serialVersionUID = -6054289411091614591L;
private Cart cart = new Cart();
  private String workingItemId;

  /* JavaBeans Properties */

  public Cart getCart() { return cart; }
  public void setCart(Cart cart) { this.cart = cart; }

  public String getWorkingItemId() { return workingItemId; }
  public void setWorkingItemId(String workingItemId) { this.workingItemId = workingItemId; }

  /* Public Methods */

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    workingItemId = null;
  }
}
