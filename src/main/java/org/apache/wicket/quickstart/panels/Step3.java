package org.apache.wicket.quickstart.panels;

import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
/**
 * 
 * @author fernando wermus
 *
 */
public class Step3 extends Panel {
	private static final long serialVersionUID = -8121779223734979200L;

	public Step3(String panelId, org.apache.wicket.model.IModel model) {
		super(panelId);
		setModel(new CompoundPropertyModel(model));
		
		add(new RequiredTextField("textField3"));
	}

}
