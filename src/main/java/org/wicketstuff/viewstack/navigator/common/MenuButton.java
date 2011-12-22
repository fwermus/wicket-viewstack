package org.wicketstuff.viewstack.navigator.common;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.viewstack.model.IViewStackModel;
import org.wicketstuff.viewstack.view.IView;

/**
 *
 * @author fwermus
 * @author dtoffe
 */
public class MenuButton extends Panel{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param id
	 * @param model
	 */
	public MenuButton(String id, IModel model)
	{
		super(id, model);

		 IModel tabCount = new AbstractReadOnlyModel()
		{
			private static final long serialVersionUID = 1L;

			public Integer getObject()
			{
				IViewStackModel viewStackModel=getViewstackModel();
				return new Integer(viewStackModel.size());
			}
		};

		WebMarkupContainer tabsContainer = new WebMarkupContainer("tabs-container");
		add(tabsContainer);
		// add the loop used to generate tab names
		tabsContainer.add(new Loop("tabs", tabCount)
		{
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			protected void populateItem(LoopItem item) {
				 int index = item.getIteration();
				IView viewItem = getViewstackModel().getView(index);
				Link button = newLink("button", (IView) viewItem);
				button.add(new AttributeModifier("value", true, viewItem.getTitleAsModel()));
				item.add(button);
			};


			@Override
			protected LoopItem newItem(int iteration)
			{
				return new LoopItem(iteration)
				{
					private static final long serialVersionUID = 1L;

					@Override
					protected void onComponentTag(ComponentTag tag)
					{
						super.onComponentTag(tag);
					}

				};
			}
		});
	}

	/**
	 * 
	 * @param linkId
	 * @param viewItem
	 * @return
	 */
	protected Link newLink(String linkId,  IView viewItem){
		return new Link(linkId, new Model(viewItem)){
			private static final long serialVersionUID = 1L;

			public void onClick(){
				getViewstackModel().setActiveView((IView) getModelObject());
			}
		};
	}

	public IViewStackModel getViewstackModel() {
		return (IViewStackModel) getModelObject();
	}

}
