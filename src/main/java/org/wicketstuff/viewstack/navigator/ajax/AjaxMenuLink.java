package org.wicketstuff.viewstack.navigator.ajax;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.viewstack.common.IViewStack;
import org.wicketstuff.viewstack.model.IViewStackModel;
import org.wicketstuff.viewstack.model.ViewStackModel;
import org.wicketstuff.viewstack.navigator.AbstractMenuLink;
import org.wicketstuff.viewstack.view.IView;

/**
 *
 * @author fernando wermus
 * @author dtoffe
 */
public class AjaxMenuLink extends AbstractMenuLink{
	private static final long serialVersionUID = 1L;
	private IViewStack viewstack;


	/**
	 * 
	 * @param id
	 * @param model
	 */
	@SuppressWarnings("unchecked")
	public AjaxMenuLink(String id, IViewStack viewstack)
	{
		super(id, (IModel) viewstack.getModel());
		setOutputMarkupId(true);
		this.viewstack=viewstack;
	}

	/**
	 * 
	 * @param linkId
	 * @param viewItem
	 * @return
	 */
	public AbstractLink  newLink(String linkId, IView viewItem){
		AjaxLink link= new IndicatingAjaxLink(linkId, new Model(viewItem)){
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isEnabled() {
				IView viewModel=(IView) getModelObject();
				return viewModel.isLinkEnabled();
			}
			@Override
			public boolean isVisible() {
				ViewStackModel vsm=(ViewStackModel) AjaxMenuLink.this.getModelObject();
				IView viewModel=(IView) getModelObject();
				return viewModel.isLinkVisible() && vsm.isVisible(viewModel);
			}
			@Override
			public void onClick(AjaxRequestTarget target) {
				preOnClick(target, getModel());
				IViewStackModel viewStackModel=(IViewStackModel) AjaxMenuLink.this.getModelObject();
				viewStackModel.setActiveView((IView) getModelObject());
				getViewstack().refrescar(target);
				target.addComponent(AjaxMenuLink.this);
				postOnClick(target);
			}
		};
		ViewStackModel vsm=(ViewStackModel) getViewstack().getModelObject();
// comentado para que se vea que se clickean las solapas
// si se comenta, en el caso de la barra de las cajas, los links no funcionan
		if (isLinkClicked()){
			link.setRenderBodyOnly(vsm.getActiveView().equals(viewItem));
		}
		
		return link;
	}
	public void preOnClick(AjaxRequestTarget target, IModel model) {
		
	}
	public void postOnClick(AjaxRequestTarget target) {
		
	}
/*
 * GETTERS & SETTERS
 */
	public IViewStack getViewstack() {
		return viewstack;
	}

	public void setViewstack(IViewStack viewstack) {
		this.viewstack = viewstack;
	}

}
