package org.wicketstuff.viewstack.common;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.viewstack.model.IViewStackModel;
import org.wicketstuff.viewstack.model.ViewStackModel;
import org.wicketstuff.viewstack.view.IView;

/**
 *
 * @author fernando wermus
 * @author dtoffe
 */
public class ViewStack extends Panel implements IViewStack
{
    
	private static final long serialVersionUID = -7710339146435944043L;
	protected static final String ACTIVE_VIEW = "activeView";

	/**
	 * 
	 * @param id
	 */
	public ViewStack(String id, IModel model)
	{
		super(id, model);
		setOutputMarkupId(true);
	}

	/**
	 *
	 * @return
	 */
//	public String getView()
//	{
//		if (getModelObject() == null) {
//			return "No view selected";
//		} else {
//			IView viewItem=(IView) getModelObject();
//			return viewItem.getTitle();
//		}
//	}

	/**
	 *
	 * @param viewItem
	 */
	public void setSelectedView(IView viewItem)
	{
		getContainer().replace((Component) viewItem.getView(ACTIVE_VIEW));
	}

	@SuppressWarnings("unchecked")
	@Override protected void onBeforeRender() {
		super.onBeforeRender();
		IViewStackModel vs=getViewStackModel();
		IView viewItem=null;
		try{
			viewItem=(IView) vs.getActiveView();
		}catch(WicketRuntimeException e){
			viewItem=vs.getFirstViewVisible();
			vs.setActiveView(viewItem);
		}
		//Panel panel=(Panel) ;
//		if (getContainer().get(ACTIVE_VIEW)!=null && getContainer().get(ACTIVE_VIEW).getClass().equals(panel.getClass())){
//			super.onBeforeRender();
//			return;
//		}
		getContainer().addOrReplace((Component) viewItem.getView(ACTIVE_VIEW));

	}
	public IViewStackModel getViewStackModel() {
		return (IViewStackModel) getModelObject();
	}

	public void superSuperOnBeforeRender(){
		super.onBeforeRender();
	}
	protected MarkupContainer getContainer() {
		return this;
	}
	public void refrescar(AjaxRequestTarget target) {
		target.addComponent(this);
	}

	public void setSelectedView(int i) {
		ViewStackModel vsm=(ViewStackModel) getModelObject();
		vsm.setActiveView(i);
	}

	public Component getActiveView() {
		return getContainer().get(ACTIVE_VIEW);
	}

}
