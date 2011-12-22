package org.wicketstuff.viewstack.navigator.ajax;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.viewstack.common.IViewStack;
import org.wicketstuff.viewstack.common.ViewStack;
import org.wicketstuff.viewstack.navigator.AbstractMenuLink;

public abstract class AbstractAjaxNavigator extends Panel {
	private static final long serialVersionUID = 8058576930382625871L;

	public AbstractAjaxNavigator(String id, IModel modelo) {
		super(id, modelo);
		setOutputMarkupId(true);
		add(newViewstack("contenido", modelo));
		add(newMenuLink("menu"));
	}

	protected AbstractMenuLink newMenuLink(String id) {
		return new AjaxMenuLink(id, getViewStack());
	}
	protected Component newViewstack(String id, IModel modelo) {
		return new ViewStack(id, modelo);
	}
	public IViewStack getViewStack() {
		return (IViewStack) get("contenido");
	}
	public AbstractMenuLink getMenuLink(){
		return (AbstractMenuLink) get("menu");
	}

}
