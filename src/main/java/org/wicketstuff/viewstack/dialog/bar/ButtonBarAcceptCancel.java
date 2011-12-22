package org.wicketstuff.viewstack.dialog.bar;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class ButtonBarAcceptCancel extends Panel {
	public ButtonBarAcceptCancel(String id) {
		super(id);
		add(new Button("accept"){
			private static final long serialVersionUID = -3370651839573850613L;
			
			@Override
			public void onSubmit() {
				ButtonBarAcceptCancel.this.accept();
			}
		});
		
		add(new Link("cancel"){
			private static final long serialVersionUID = -3370651839573850613L;

			@Override
			public void onClick() {
				ButtonBarAcceptCancel.this.cancel();
				
			}
		});
	}

//	protected abstract Component getFeedback();

	protected void accept() {

		
	}

	protected void cancel() {

	}
	
	
}
