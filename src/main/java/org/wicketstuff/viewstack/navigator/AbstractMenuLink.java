package org.wicketstuff.viewstack.navigator;


import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;
import org.wicketstuff.viewstack.model.IViewStackModel;
import org.wicketstuff.viewstack.model.ViewStackModel;
import org.wicketstuff.viewstack.view.IView; 


/**
 *
 * @author fernando wermus
 * @author dtoffe
 */
public abstract class AbstractMenuLink extends Panel{
	private static final long serialVersionUID = 1L;
	
	private boolean linkClicked=true;
	
	/**
	 * 
	 * @param id
	 * @param model
	 */
	public AbstractMenuLink(String id, IModel model)
	{
		super(id, model);

		 IModel tabCount = new AbstractReadOnlyModel()
		{
			private static final long serialVersionUID = 1L;

			public Integer getObject()
			{
				IViewStackModel viewStackModel=(IViewStackModel) getModelObject();
				return new Integer(viewStackModel.size());
			}
		};

		WebMarkupContainer tabsContainer = new WebMarkupContainer("tabs-container");
		remove(tabsContainer);
		add(tabsContainer);
		// add the loop used to generate tab names
		tabsContainer.add(new Loop("tabs", tabCount)
		{
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			protected void populateItem(LoopItem item) {
				int index = item.getIteration();
				ViewStackModel viewStackModel=(ViewStackModel) AbstractMenuLink.this.getModelObject();
				//viewStackModel=viewStackModel.filter();
				IView view=viewStackModel.getInternalView(index);
				AbstractLink titleLink = newLink("link", view);
				Component title = newTitle("title", view.getTitleAsModel());
				titleLink.add(title);
				boolean visible=!"".equals(view.getLinkImage()) && viewStackModel.isVisible(view);
				titleLink.add(new ContextImage("imageLink", view.getLinkImageAsModel()).setVisible(visible));
				
				item.add(titleLink);
				
			};
		});
		
	}

	/**
	 * 
	 * @param linkId
	 * @param viewItem
	 * @return
	 */
	public abstract AbstractLink newLink(String linkId, IView viewItem);

	/**
	 *
	 * @param titleId
	 * @param titleModel
	 * @param index
	 * @return
	 */
	protected Component newTitle(String titleId, IModel titleModel)
	{
		return new Label(titleId, titleModel);
	}
	public WebMarkupContainer getTabsContainer(){
		return (WebMarkupContainer) get("tabs-container");
	}
	public boolean isLinkClicked() {
		return linkClicked;
	}

	public void setLinkClicked(boolean linkClicked) {
		this.linkClicked = linkClicked;
	}
}
//BorderSelected b=new BorderSelected("borderSelected");
//b.add(titleLink);

//b.getBodyContainer().setVisible(false);
//b.setVisible(false);
//b.setRenderBodyOnly(true);
//titleLink.setEnabled(!viewStackModel.getActiveView().equals(view));