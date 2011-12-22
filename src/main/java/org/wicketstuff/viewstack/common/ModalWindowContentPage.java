package org.wicketstuff.viewstack.common;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.viewstack.model.ViewStackModel;
import org.wicketstuff.viewstack.view.IView;
/**
 * FIXME Cuando uso un resource model pareciera que tengo que agregar a esta clase un .xml
 * Sin embargo, esta clase pertenece a wicketstuff. Ver!!
 * 
 * @author fernando wermus
 *
 */
public class ModalWindowContentPage extends WebPage {

	public ModalWindowContentPage(IModel model) {
		super(model);
		if (model==null){
			throw new RuntimeException("el modelo no puede ser nulo");
		}
		add(new EmptyPanel("c"));
	}

	@Override
	protected void onBeforeRender() {
		ViewStackModel vs=(ViewStackModel) getModelObject();
		IView view=(IView)vs.getActiveView();

		addOrReplace((Component) view.getView("c"));
		super.onBeforeRender();
	}

	public Panel getPanel() {
		return (Panel) get("c");
	}
}
