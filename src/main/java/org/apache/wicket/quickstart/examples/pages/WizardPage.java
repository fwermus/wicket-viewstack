package org.apache.wicket.quickstart.examples.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.quickstart.examples.HomePage;
import org.apache.wicket.quickstart.examples.model.StepsModel;
import org.apache.wicket.quickstart.panels.Step1;
import org.apache.wicket.quickstart.panels.Step2;
import org.apache.wicket.quickstart.panels.Step3;
import org.wicketstuff.viewstack.model.WizardModel;
import org.wicketstuff.viewstack.view.ViewModel;
import org.wicketstuff.viewstack.wizard.AbstractWizard;
/**
 * 
 * @author fernando wermus
 *
 */
public class WizardPage extends WebPage {
	public WizardPage(){
		final IModel stepsModel=new Model(new StepsModel());
		
		final WizardModel vsModel = new WizardModel();
		vsModel.add(new ViewModel("Suggestions", "step1") {
		    private static final long serialVersionUID = 1L;

		    @Override
		    public Object getView(String panelId)
		    {
		    	return new Step1(panelId, stepsModel);
		    }
		});

		vsModel.add(new ViewModel("Errors", "step2"){
		    private static final long serialVersionUID = 1L;

		    @Override
		    public Object getView(String panelId)
		    {
		    	return new Step2(panelId, stepsModel);
		    }
		});

		vsModel.add(new ViewModel("Enhancements", "step3"){
		    private static final long serialVersionUID = 1L;

		    @Override
		    public Object getView(String panelId)
		    {
		    	return new Step3(panelId, stepsModel);
		    }

		});
		

		add(new AbstractWizard("wizard", stepsModel, new Model(vsModel)){
			private static final long serialVersionUID = -729766351590382028L;

			@Override
			protected void onCancel(AjaxRequestTarget target) {
				setResponsePage(HomePage.class);
			}

			@Override
			protected void onFinish(AjaxRequestTarget target) {
				setResponsePage(HomePage.class);
			}
			
		});
	}
}
