package org.wicketstuff.viewstack.wizard;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.viewstack.common.ViewStackWithForm;
import org.wicketstuff.viewstack.model.IWizardModel;
import org.wicketstuff.viewstack.model.WizardModel;
import org.wicketstuff.viewstack.navigator.ajax.AbstractWizardButtonBar;
import org.wicketstuff.viewstack.navigator.common.MenuInformationStatus;
import org.wicketstuff.viewstack.navigator.common.MenuStepStatus;

/**
 *
 * @author fernando wermus
 */
public abstract class AbstractWizard extends Panel {

	private static final long serialVersionUID = 1L;
	
	
	@SuppressWarnings("unchecked")
	public AbstractWizard(String id, IModel stepModel, IModel wizardModel) {
		super(id, stepModel);
		init(wizardModel);
		init();
	}
	
	public AbstractWizard(String id, IModel stepModel) {
		super(id, stepModel);
		init(new Model(new WizardModel()));
	}	

	public void init(){
		IWizardModel w=(IWizardModel) getWizardModel();
		w.setActiveView(w.getView(0));
	}
	private void init(IModel wizardModel) {

		ViewStackWithForm viewstack = new ViewStackWithForm("contents", wizardModel);
		
		MenuStepStatus menuCurrentStatus = new MenuStepStatus("menuCurrentStatus", wizardModel);
		MenuInformationStatus menuInformationStatus = new MenuInformationStatus("menuInformationStatus", wizardModel);

		add(new AbstractWizardButtonBar("wizardButtonBar", (Form) viewstack.getContainer(), wizardModel){
			private static final long serialVersionUID = 9049378794116903120L;

			@Override
			public void applyState(AjaxRequestTarget target) {
				target.addComponent(AbstractWizard.this.get("menuCurrentStatus"));
				target.addComponent(AbstractWizard.this.get("menuInformationStatus"));
				target.addComponent(AbstractWizard.this.get("contents"));
			}

			@Override
			public void finish(AjaxRequestTarget target) {
				AbstractWizard.this.onFinish(target);
				
			}

			@Override
			public void error(AjaxRequestTarget target) {
				target.addComponent(AbstractWizard.this.get("contents:form:feedback"));
			}

			@Override
			public void cancel(AjaxRequestTarget target) {
				AbstractWizard.this.onCancel(target);
			}
			
		});

		
		add(viewstack);
		add(menuCurrentStatus);
		add(menuInformationStatus);
		
	}

	public WizardModel getWizardModel(){
		return (WizardModel) get("contents").getModelObject();
	}

	protected void close(AjaxRequestTarget target) {
		
	}
	
	protected abstract void onFinish(AjaxRequestTarget target);
	protected abstract void onCancel(AjaxRequestTarget target);
}

