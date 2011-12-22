package org.wicketstuff.viewstack.navigator.common;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.wicketstuff.viewstack.model.IWizardModel;
import org.wicketstuff.viewstack.navigator.AbstractMenuStatus;

/**
 *
 * @author fwermus
 */
public class MenuInformationStatus extends AbstractMenuStatus{
	private static final long serialVersionUID = -2298041628407430460L;

	public MenuInformationStatus(String id, IModel model) {
		super(id,model);
	}
		
	@Override protected Component newTitle(String titleId, String titleModel) {
		IWizardModel wizardModel=(IWizardModel) getModelObject();
		return new Label(titleId, new ResourceModel(wizardModel.getActiveView().getName()));
	}

}
