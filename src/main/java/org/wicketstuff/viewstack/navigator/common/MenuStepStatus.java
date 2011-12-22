package org.wicketstuff.viewstack.navigator.common;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.viewstack.navigator.AbstractMenuStatus;

/**
 *
 * @author fwermus
 */
public class MenuStepStatus extends AbstractMenuStatus{
	private static final long serialVersionUID = 1468059511340842403L;

	public MenuStepStatus(String id, IModel model) {
		super(id,model);
	}
	protected Component newTitle(String titleId, String titleModel)
	{
		return new Label(titleId, new Model(titleModel));
	}

}
