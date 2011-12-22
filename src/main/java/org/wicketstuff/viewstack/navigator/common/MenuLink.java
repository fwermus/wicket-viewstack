package org.wicketstuff.viewstack.navigator.common;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.viewstack.model.IViewStackModel;
import org.wicketstuff.viewstack.model.ViewStackModel;
import org.wicketstuff.viewstack.navigator.AbstractMenuLink;
import org.wicketstuff.viewstack.navigator.ajax.AjaxMenuLink;
import org.wicketstuff.viewstack.view.IView;

/**
 *
 * @author fwermus
 * @author dtoffe
 */
public class MenuLink extends AbstractMenuLink{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param id
	 * @param model
	 */
	public MenuLink(String id, IModel model)
	{
		super(id, model);
	}

	/**
	 * 
	 * @param linkId
	 * @param viewItem
	 * @return
	 */
	public Link newLink(String linkId, IView viewItem){
		Link link= new Link(linkId, new Model(viewItem)){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isEnabled() {
				IView viewModel=(IView) getModelObject();
				return viewModel.isLinkEnabled();
			}
			@Override
			public boolean isVisible() {
				ViewStackModel vsm=(ViewStackModel) MenuLink.this.getModelObject();
				IView viewModel=(IView) getModelObject();
				return viewModel.isLinkVisible() && vsm.isVisible(viewModel);
			}
			
			public void onClick(){
				IViewStackModel viewStackModel=(IViewStackModel) MenuLink.this.getModelObject();
				viewStackModel.setActiveView((IView) getModelObject());
				
				
			}
		};
		IViewStackModel viewStackModel=(IViewStackModel) MenuLink.this.getModelObject();
		link.setRenderBodyOnly(viewStackModel.getActiveView().equals(link.getModelObject()));
		return link;
	}


}
