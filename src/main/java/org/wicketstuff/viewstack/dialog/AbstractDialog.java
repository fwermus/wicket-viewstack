package org.wicketstuff.viewstack.dialog;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public abstract class AbstractDialog extends Panel{
	private static final long serialVersionUID = -9218731428927374508L;

	public AbstractDialog(String id, IModel model) {
		super(id, model);
		setModel(model);
		setOutputMarkupId(true);
		
		add(new Form("form"){
			private static final long serialVersionUID = -7934707858983009146L;
			@Override
			protected void onError() {
				AbstractDialog.this.onError();
				super.onError();
			}
		});
		getForm().add(newPanelButton("buttonBar", model));
		//getForm().add(new FeedbackPanel("feedback").setOutputMarkupId(true));
		Component c=getContent("view");
		c.setModel(getModel());
		getForm().add(c);
		getForm().add(new FeedbackPanel("feedback"));
	}
	
	protected void onError() {
		
	}

	public AbstractDialog(String id, IModel model, String zz) {
		super(id, model);
		setModel(model);
		setOutputMarkupId(true);
		
		add(new Form("form"));
		getForm().add(newPanelButton("buttonBar", model));
		getForm().add(new FeedbackPanel("feedback").setOutputMarkupId(true));
	}
	
/*
 * GETTER - SETTERS
 */
	public Form getForm(){
		return (Form) get("form");
	}
//	protected Component getFeedback() {
//		return get("form:feedback");
//	}
	public Component getContent(){
		return get("form:view");
	}
/*
 * ABSTRACTS METHODS
 */
	protected abstract Panel newPanelButton(String id, IModel model);
	public abstract Component getContent(String id);
}
