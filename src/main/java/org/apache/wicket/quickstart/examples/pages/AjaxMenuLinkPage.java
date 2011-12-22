package org.apache.wicket.quickstart.examples.pages;


import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.quickstart.panels.Panel1;
import org.apache.wicket.quickstart.panels.Panel2;
import org.apache.wicket.quickstart.panels.Panel3;
import org.wicketstuff.viewstack.common.IViewStack;
import org.wicketstuff.viewstack.common.ViewStack;
import org.wicketstuff.viewstack.model.IViewStackModel;
import org.wicketstuff.viewstack.model.ViewStackModel;
import org.wicketstuff.viewstack.navigator.ajax.AjaxMenuLink;
import org.wicketstuff.viewstack.view.ViewModel;

/**
 *
 * @author fernando wermus
 * @author dtoffe
 */
public class AjaxMenuLinkPage extends WebPage {

	private static final long serialVersionUID = 1L;

	public AjaxMenuLinkPage() {
		// Add the simplest type of label
		IViewStackModel vsModel = new ViewStackModel();
		vsModel.add(new ViewModel("Suggestions","step1") {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public Object getView(String panelId)
		    {
			return new Panel1(panelId);
		    }
		});

		vsModel.add(new ViewModel("Errors", "step2"){

		    private static final long serialVersionUID = 1L;

		    @Override
		    public Object getView(String panelId)
		    {
			return new Panel2(panelId);
		    }
		});

		vsModel.add(new ViewModel("Enhancements","step3"){

		    private static final long serialVersionUID = 1L;

		    @Override
		    public Object getView(String panelId)
		    {
			return new Panel3(panelId);
		    }

		});
		vsModel.setActiveView(vsModel.getView(0));
		
		IViewStack viewstack = new ViewStack("contents", new Model(vsModel));
		AjaxMenuLink menuLink = new AjaxMenuLink("menuBarraDerecha", viewstack);

		add((Component)viewstack);
		add(menuLink);
	}

}
