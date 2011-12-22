package org.wicketstuff.viewstack.navigator;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.viewstack.model.IWizardModel;

/**
 *
 * @author fernando wermus
 */
public abstract class AbstractMenuStatus extends Panel
{
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @param id
	 * @param items
	 */
	public AbstractMenuStatus(String id, IModel model) {
		super(id,model);
		setOutputMarkupId(true);

		MarkupContainer tabsContainer = new WebMarkupContainer("tabs-container");
		WebMarkupContainer tabs=new WebMarkupContainer("tabs");
		tabs.add(newTitle("title", "Without status"));
		tabsContainer.add(tabs);
		add(tabsContainer);
	}

	protected abstract Component newTitle(String titleId, String titleModel);

	@Override protected void onBeforeRender() {
		super.onBeforeRender();
		Component tabs=(Component) get("tabs-container:tabs:title");
		IWizardModel wizardModel=(IWizardModel) getModelObject();
		tabs.replaceWith(newTitle("title", wizardModel.getActiveView().getTitle()));
	}

	public void onEmptyModel() {
		Label title=(Label) get("tabs-container:tabs:title");
		title.setModel(new Model("Without status"));
	}

}
