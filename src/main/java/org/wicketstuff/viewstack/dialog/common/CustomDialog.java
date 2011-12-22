package org.wicketstuff.viewstack.dialog.common;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.viewstack.dialog.AbstractDialog;
import org.wicketstuff.viewstack.dialog.bar.ButtonBarAcceptCancel;



public abstract class CustomDialog extends AbstractDialog{
	private static final long serialVersionUID = -6192437659355415853L;
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(CustomDialog.class);

	public CustomDialog(String id, IModel modelo) {
		super(id, modelo);
	}
	

	protected Panel newPanelButton(String id, IModel model){
		return new ButtonBarAcceptCancel(id){
			private static final long serialVersionUID = 5199801413989505765L;

//			@Override
//			protected Component getFeedback() {
//				return CustomDialog.this.getFeedback();
//			}
			@Override
			protected void accept() {
				CustomDialog.this.accept();
			}
			@Override
			protected void cancel() {
				CustomDialog.this.cancel();
			}
			
		};
	}
	protected abstract void accept();
	public abstract void cancel();
}
