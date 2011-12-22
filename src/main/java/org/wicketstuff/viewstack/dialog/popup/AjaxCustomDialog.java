package org.wicketstuff.viewstack.dialog.popup;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.viewstack.dialog.AbstractDialog;
import org.wicketstuff.viewstack.dialog.bar.AjaxButtonBarAcceptCancel;



public abstract class AjaxCustomDialog extends AbstractDialog{
	private static final long serialVersionUID = -6192437659355415853L;
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(AjaxCustomDialog.class);

	public AjaxCustomDialog(String id, IModel modelo) {
		super(id, modelo);
	}
	

	protected Panel newPanelButton(String id, IModel model){
		return new AjaxButtonBarAcceptCancel(id){
			private static final long serialVersionUID = 5199801413989505765L;

			@Override
			protected void accept(AjaxRequestTarget target, Form form) {
				AjaxCustomDialog.this.accept(target, form);
				closed(target);
			}

			@Override
			protected void cancel(AjaxRequestTarget target) {
				AjaxCustomDialog.this.cancel(target);
			}

			@Override
			public void closed(AjaxRequestTarget target) {
				// no hacemos nada
			}
			
			@Override
			protected void error(AjaxRequestTarget target, Form form) {
				target.addComponent(AjaxCustomDialog.this);
			}
			@Override
			protected String javascriptAcceptDecorator() {
				return AjaxCustomDialog.this.javascriptAcceptDecorator();
			}
		};
	}
	protected abstract void accept(AjaxRequestTarget target, Form form);
	protected abstract void cancel(AjaxRequestTarget target);
	protected String javascriptAcceptDecorator() {
		return "";
	}
}
