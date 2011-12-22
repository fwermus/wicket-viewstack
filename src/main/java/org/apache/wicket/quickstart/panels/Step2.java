package org.apache.wicket.quickstart.panels;

import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
/**
 * 
 * @author fernando wermus
 *
 */
public class Step2 extends Panel {
	private static final long serialVersionUID = 110432677283380875L;

	public Step2(String panelId, IModel model) {
		super(panelId);
		setModel(new CompoundPropertyModel(model));
		
		add(new RequiredTextField("textField2"));
	}

}
