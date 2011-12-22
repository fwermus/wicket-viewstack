package org.wicketstuff.viewstack.view;

import java.io.Serializable;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

/**
 * Subinterface of IItem that defnes behaviour for View Items,
 * adding a Link and a Panel
 * 
 * @author dtoffe
 */
public interface IView extends Serializable
{

	/**
	 *
	 * @param id 
	 * @return The panel for this view
	 */
	public Object getView(String id);

	/**
	 *
	 * @param id the id of the link
	 * @return a Link to this view
	 */
	public Link getLink(String id);
	
	/**
	 *
	 * @return the title of this view as an IModel
	 */
	public IModel getTitleAsModel();
	/**
	 * 
	 * @return the tifle of this view
	 */
	public String getTitle();
	public String getName();

	public boolean isLinkEnabled();

	public boolean isLinkVisible();

	public IModel getLinkImageAsModel();

	public String getLinkImage();
}
