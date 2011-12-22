package org.wicketstuff.viewstack.common;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
/**
 * 
 * @author fernando wermus
 *
 */
public class ViewStackWithForm extends ViewStack implements IPopable { //, IAjaxIndicatorAware
	private static final long serialVersionUID = 1650382560227285131L;

	public ViewStackWithForm(String id, IModel model) {
		super(id, model);
		setOutputMarkupId(true);
		add(newForm("form"));
		
	}
	
	protected Form newForm(String id){		
		Form form=  new Form(id);
		form.add(new FeedbackPanel("feedback"));
		return form;
	}
	

	@Override protected void onBeforeRender() {
//		visitChildren(new VerifiedErrors(this));
		// tengo que mostrar el mismo panel, asi que no realizo cambios
		if (getForm().hasError()){
			superSuperOnBeforeRender();
			return;
		}
		super.onBeforeRender();
	}
/*
 * GETTERS & SETTERS
 */
	@SuppressWarnings("unused")
	public FeedbackPanel getFeedBack() {
		return (FeedbackPanel) get("form:feedback");
	}

	@Override
	public MarkupContainer getContainer() {
		return (MarkupContainer) get("form");
	}
	
	public Form getForm() {
		return (Form) get("form");
	}

	public void closed(AjaxRequestTarget target) {
		
	}


//	public String getAjaxIndicatorMarkupId() {
//		return "veil";
//	}


}
