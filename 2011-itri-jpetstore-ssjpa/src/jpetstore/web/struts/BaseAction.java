package jpetstore.web.struts;

import jpetstore.domain.logic.PetStoreFacade;

import org.apache.struts.action.Action;

public abstract class BaseAction extends Action
{

    private PetStoreFacade petStore;

//    public void setServlet(ActionServlet actionServlet)
//    {
//        super.setServlet(actionServlet);
//        ServletContext servletContext = actionServlet.getServletContext();
//        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
//        petStore = (PetStoreFacade) wac.getBean("petStore");
//    }

    protected PetStoreFacade getPetStore() // 只有 extends BaseAction的類別才能存取
    {
        return petStore;
    }

    public void setPetStore(PetStoreFacade petStore)
    {
        this.petStore = petStore;
    }

}
