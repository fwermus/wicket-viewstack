package org.apache.wicket.quickstart.panels;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
/**
 * 
 * @author fernando wermus
 *
 */
public class Step1 extends Panel {
	private static final long serialVersionUID = -6334163300311197857L;

	public Step1(String panelId, IModel model) {
		super(panelId);
		setModel(new CompoundPropertyModel(model));
		//add(new RequiredTextField("textField1"));
	}

}
