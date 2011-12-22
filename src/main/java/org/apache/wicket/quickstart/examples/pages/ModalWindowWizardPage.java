package org.apache.wicket.quickstart.examples.pages;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.quickstart.examples.model.StepsModel;
import org.apache.wicket.quickstart.panels.Step1;
import org.apache.wicket.quickstart.panels.Step2;
import org.apache.wicket.quickstart.panels.Step3;
import org.wicketstuff.viewstack.model.WizardModel;
import org.wicketstuff.viewstack.view.ViewModel;
import org.wicketstuff.viewstack.wizard.WizardModalWindowPage;
/**
 * 
 * @author fernando wermus
 *
 */
public class ModalWindowWizardPage extends WebPage {
	private static final long serialVersionUID = 1L;
	private ModalWindow dialog;
	private WizardModalWindowPage dialogPage;
	
	
	public ModalWindowWizardPage(){
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
		// instructions to show a modal window
		init(new Model(vsModel));
	}


	private void init(final IModel wizardModel) {
		setDialog(new ModalWindow("dialog"));
		
		// tamanio de widget formacion. Por ahora, todos los dialogos tienen el mismo tamanio.
		getDialog().setTitle("Dialogo");
		getDialog().setInitialWidth(825); 
		getDialog().setInitialHeight(590); 
		getDialog().setResizable(false); 
		getDialog().setWidthUnit("px"); 
		getDialog().setHeightUnit("px"); 
				
		add(getDialog());

	    getDialog().setPageCreator(new ModalWindow.PageCreator(){
	    	private static final long serialVersionUID = -8312779555190942673L;

			public Page createPage(){
	            return new WizardModalWindowPage(wizardModel){
					@Override
					protected void cancel(AjaxRequestTarget target) {
						getDialog().close(target);
					}

					@Override
					protected void finish(AjaxRequestTarget target) {
						getDialog().close(target);
					}
	            };
	        }
	    });
	    
	    add(new AjaxLink("show"){
			private static final long serialVersionUID = 5444799235222536878L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				getDialog().show(target);
				
			}
	    	
	    });
	}
	
	private void setDialog(ModalWindow dialog) {
		this.dialog=dialog;
	}
	private ModalWindow getDialog() {
		return dialog;
	}
	@SuppressWarnings("unused")
	private void setDialogPage(WizardModalWindowPage dialogPage) {
		this.dialogPage=dialogPage;
	}
	public WizardModalWindowPage getDialogPage() {
		return dialogPage;
	}
}
