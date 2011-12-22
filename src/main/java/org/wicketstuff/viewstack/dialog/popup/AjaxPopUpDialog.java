package org.wicketstuff.viewstack.dialog.popup;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.wicketstuff.viewstack.common.IPopable;
import org.wicketstuff.viewstack.dialog.AbstractDialog;
import org.wicketstuff.viewstack.dialog.bar.AjaxButtonBarAcceptCancel;


public abstract class AjaxPopUpDialog extends AbstractDialog implements IPopable, IDetachable{
	private static final long serialVersionUID = 3729335309867101463L;
	
	public AjaxPopUpDialog(String id, IModel model, String content) {
		super(id, model, content);
		Component c=getContent("view");
		c.setModel(new ResourceModel(content));
		getForm().add(c);
	}
	
	@Override
	public Component getContent(String id) {
		return new Label("view").setEscapeModelStrings(false);
	}
	@Override
	protected Panel newPanelButton(String id, IModel model) {
		return new AjaxButtonBarAcceptCancel(id){
			private static final long serialVersionUID = 1022917543520496210L;

//			@Override
//			protected Component getFeedback() {
//				return getFeedback();
//			}
			@Override
			protected void accept(AjaxRequestTarget target, Form form) {
				if (AjaxPopUpDialog.this.accept(target, form)){
					closed(target);
				}
			}
			@Override
			protected void cancel(AjaxRequestTarget target) {
				AjaxPopUpDialog.this.closed(target);
			}

			@Override
			public void closed(AjaxRequestTarget target) {
				AjaxPopUpDialog.this.closed(target);
			}
			@Override
			protected void error(AjaxRequestTarget target, Form form) {
				target.addComponent(AjaxPopUpDialog.this);
				
			}
		};
	}

	protected  boolean accept(AjaxRequestTarget target, Form form){
		return true;
	};
	public  void closed(AjaxRequestTarget target){};
	
}
/*
 * 		Form formulario=new Form("formulario");
		AjaxButton aceptar=new AjaxButton("accept", model){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				AjaxPopUpDialog.this.onSubmit(target, form);
				close(target);
			}

			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
		        return new AjaxCallDecorator() {
		            private static final long serialVersionUID = 1L;
					@Override
					public CharSequence decorateScript(CharSequence script) {
						return "this.className='ButtonDesactivado';this.disabled=true;" + script;
					}
		        };
			}
		};
		AjaxLink cancelar=new AjaxLink("cancel", model){
			private static final long serialVersionUID = 1L;

			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
		        return new AjaxCallDecorator() {
		            private static final long serialVersionUID = 1L;
					@Override
					public CharSequence decorateScript(CharSequence script) {
						return "this.className='ButtonDesactivado';this.disabled=true;" + script;
					}
		        };
			}

			@Override
			public void onClick(AjaxRequestTarget target) {
				close(target);
			}
		};
		formulario.add(aceptar);
		formulario.add(cancelar);

		formulario.add(new Label("contenido", new ResourceModel(content)).setEscapeModelStrings(false));
		add(formulario);
 */
