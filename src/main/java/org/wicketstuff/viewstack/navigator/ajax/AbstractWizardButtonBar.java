package org.wicketstuff.viewstack.navigator.ajax;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.viewstack.model.IWizardModel;

/**
 * 
 * @author fernando wermus
 *
 */
public abstract class AbstractWizardButtonBar extends Panel {
	private static final long serialVersionUID = 5429574053410784873L;


	public AbstractWizardButtonBar(String id, Form form, IModel model) {
		super(id);
		setOutputMarkupId(true);
		add(new Next("next", form, model));
		add(new Previous("previous", model));
		add(new Finish("finish", form, model));
		add(new Cancel("cancel"));
	}
	
	class Next extends AjaxButton{
		private static final long serialVersionUID = -8650348314470529529L;
		
		public Next(String id, Form form, IModel model){
			super(id, form);
			setModel(model);
		}
		
		@Override
		public boolean isEnabled() {
			IWizardModel model=(IWizardModel) getModelObject();
			return (model.hasNext());
		}

		@Override
		protected void onSubmit(AjaxRequestTarget target, Form form) {
			IWizardModel model=(IWizardModel) getModelObject();
			model.next();
			refresh(target);
			applyState(target);
		}
		@Override
		protected void onError(AjaxRequestTarget target, Form form) {
			AbstractWizardButtonBar.this.error(target);
			super.onError(target, form);
		}
	}


	class Previous extends AjaxLink{
		private static final long serialVersionUID = 3245649126067280571L;
		public Previous(String id, IModel model){
			super(id);
			setModel(model);
		}
		@Override
		public boolean isEnabled() {
			IWizardModel model=(IWizardModel) getModelObject();
			return (model.hasPrevious());
		}
		@Override
		public void onClick(AjaxRequestTarget target) {
			IWizardModel model=(IWizardModel) getModelObject();
			model.previous();
			refresh(target);
			applyState(target);
		}
		

	}
	
	class Finish extends AjaxButton{
		private static final long serialVersionUID = 3245649126067280571L;
		public Finish(String id, Form form, IModel model){
			super(id, form);
			setModel(model);
		}


		@Override
		public boolean isEnabled() {
			IWizardModel model=(IWizardModel) getModelObject();
			return (model.isFinish());
		}

		@Override
		protected void onSubmit(AjaxRequestTarget target, Form form) {
			target.addComponent(AbstractWizardButtonBar.this);
			refresh(target);
			finish(target);
		}
		@Override
		protected void onError(AjaxRequestTarget target, Form form) {
			AbstractWizardButtonBar.this.error(target);
			super.onError(target, form);
		}

	}
	class Cancel extends AjaxLink{
		private static final long serialVersionUID = -8650348314470529529L;
		
		public Cancel(String id){
			super(id);
		}


		@Override
		public void onClick(AjaxRequestTarget target) {
			target.addComponent(AbstractWizardButtonBar.this);
			refresh(target);
			cancel(target);
		}
	}
	private void refresh(AjaxRequestTarget target) {
		target.addComponent(this);
	}
	public abstract void error(AjaxRequestTarget target);
	public abstract void applyState(AjaxRequestTarget target);
	public abstract void finish(AjaxRequestTarget target);
	public abstract void cancel(AjaxRequestTarget target);
}
