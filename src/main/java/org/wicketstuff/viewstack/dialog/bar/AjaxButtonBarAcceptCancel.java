package org.wicketstuff.viewstack.dialog.bar;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.calldecorator.AjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.viewstack.common.IPopable;
/**
 * Esta botonera funciona para los customs como para los dialogos
 * @author fernandowermus
 *
 */
public abstract class AjaxButtonBarAcceptCancel extends Panel implements IPopable{
	public AjaxButtonBarAcceptCancel(String id) {
		super(id);
		setOutputMarkupId(true);
		add(new AjaxButton("accept"){
			private static final long serialVersionUID = -3370651839573850613L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				AjaxButtonBarAcceptCancel.this.accept(target, form);
				//ModalWindow.closeCurrent(target);
			}
			@Override
			protected void onError(AjaxRequestTarget target, Form form) {
				AjaxButtonBarAcceptCancel.this.error(target, form); //.getFeedback()
			}
			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
		        return new AjaxCallDecorator() {
		            private static final long serialVersionUID = 1L;
					@Override
					public CharSequence decorateScript(CharSequence script) {
						return javascriptAcceptDecorator() + script;
					}

		        };
			}
		});
		add(new AjaxLink("cancel"){
			private static final long serialVersionUID = -3370651839573850613L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				AjaxButtonBarAcceptCancel.this.cancel(target);
				
			}
//			@Override
//			protected IAjaxCallDecorator getAjaxCallDecorator() {
//		        return new AjaxCallDecorator() {
//		            private static final long serialVersionUID = 1L;
//					@Override
//					public CharSequence decorateScript(CharSequence script) {
//						return "this.className='ButtonDesactivado';this.disabled=true;" + script;
//					}
//		        };
//			}
		});
	}
	public AjaxButtonBarAcceptCancel(String id, IModel model){
		this(id);
		setModel(model);
	}
	protected String javascriptAcceptDecorator() {
		return "";
	}
	protected abstract void error(AjaxRequestTarget target, Form form);
	
//	 {
//			//target.addComponent(getFeedback());
//		}

//	protected abstract Component getFeedback();

	protected abstract void accept(AjaxRequestTarget target, Form form);

	protected abstract void cancel(AjaxRequestTarget target);
	public abstract void closed(AjaxRequestTarget target);

}
