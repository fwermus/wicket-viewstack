package org.wicketstuff.viewstack.wizard;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
/**
 * 
 * @author fernando wermus
 *
 */
public abstract class WizardModalWindowPage extends WebPage {

	public WizardModalWindowPage(IModel wizardModel) {
		super(wizardModel);
		add(new AbstractWizard("wizard", new Model(""), wizardModel){
			private static final long serialVersionUID = -5962429231487690285L;
			
			@Override
			protected void onFinish(AjaxRequestTarget target) {
				WizardModalWindowPage.this.finish(target);
			}
			
			@Override
			protected void onCancel(AjaxRequestTarget target) {
				WizardModalWindowPage.this.cancel(target);
			}
			
		});
	}

	protected abstract void cancel(AjaxRequestTarget target) ;

	protected abstract void finish(AjaxRequestTarget target);
	
	
}
