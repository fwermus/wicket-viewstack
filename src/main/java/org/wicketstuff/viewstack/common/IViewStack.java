package org.wicketstuff.viewstack.common;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.wicketstuff.viewstack.view.IView;
/**
 * 
 * @author fernando wermus
 *
 */
public interface IViewStack {

	IModel getModel();

	void setSelectedView(IView modelObject);

	void refrescar(AjaxRequestTarget target);

	public Component setModel(final IModel model);

	Object getModelObject();

	Component getActiveView();

}
