package org.apache.wicket.quickstart.examples.pages;


import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.quickstart.panels.Panel1;
import org.apache.wicket.quickstart.panels.Panel2;
import org.apache.wicket.quickstart.panels.Panel3;
import org.wicketstuff.viewstack.common.ViewStack;
import org.wicketstuff.viewstack.model.IViewStackModel;
import org.wicketstuff.viewstack.model.ViewStackModel;
import org.wicketstuff.viewstack.navigator.common.MenuButton;
import org.wicketstuff.viewstack.navigator.common.MenuLink;
import org.wicketstuff.viewstack.view.ViewModel;

/**
 *
 * @author fernando wermus
 * @author dtoffe
 */
public class DoubleMenuLinkPage extends WebPage {

	private static final long serialVersionUID = 1L;

	public DoubleMenuLinkPage() {
		// Add the simplest type of label
		IViewStackModel listView = new ViewStackModel();
		listView.add(new ViewModel("Suggestions","step1") {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public Object getView(String panelId)
		    {
			return new Panel1(panelId);
		    }
		});

		listView.add(new ViewModel("Errors","step2"){

		    private static final long serialVersionUID = 1L;

		    @Override
		    public Object getView(String panelId)
		    {
			return new Panel2(panelId);
		    }
		});

		listView.add(new ViewModel("Enhancements","step3"){

		    private static final long serialVersionUID = 1L;

		    @Override
		    public Object getView(String panelId)
		    {
			return new Panel3(panelId);
		    }

		});
		listView.setActiveView(listView.getView(0));
		
		setModel(new Model(listView));
		ViewStack viewstack = new ViewStack("contents", getModel());
		MenuLink menuLink = new MenuLink("menuLink", getModel());
		MenuButton menuButton = new MenuButton("menuButton",getModel());
		
		add(viewstack);
		add(menuLink);
		add(menuButton);
		
	}

}
