package org.wicketstuff.viewstack.view;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.viewstack.statement.IPredicate;


/**
 *
 * @author fwemrus
 */
public abstract class ViewModel implements IView
{

	private static final long serialVersionUID = 1L;

	private IModel title;
	private String toolTip;
	private IModel name;

	private IModel linkImage=new Model("");

	private Model condicional;

	public abstract Object getView(String id);
	
	public Link getLink(String id)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	};
	/**
	 * 
	 * @param title
	 * @param itemVisible
	 */
	public ViewModel(String title)
	{
		setTitle(title);
		setName(String.valueOf(Math.random()));
	}
	/**
	 * 
	 * @param title
	 * @param itemVisible
	 */
	public ViewModel(String title, String name)
	{
		setTitle(title);
		setName(name);
	}
	public ViewModel(IModel title) {
		this.title=title;
		setName(String.valueOf(Math.random()));
	}

	public ViewModel(IModel title, String name) {
		setTitleAsModel(title);
		setName(name);
	}

	public ViewModel(String title, IModel nameAsModel) {
		setTitle(title);
		setNameAsModel(nameAsModel);
	}

	public ViewModel(String title, String name, String linkImage) {
		this(title, name);
		setLinkImage(linkImage);
	}

	public ViewModel(Model model, IPredicate condicional) {
		this.condicional=new Model(condicional);
	}

	private void setLinkImage(String linkImage) {
		this.linkImage=new Model(linkImage);
	}

	public void setNameAsModel(IModel name) {
		this.name=name;
	}

	private void setTitleAsModel(IModel title) {
		this.title=title;
	}

	public IModel getTitleAsModel()
	{
		return title;
	}


	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return (String) title.getObject();
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title)
	{
		this.title = new Model(title);
	}
	public String getToolTip() {
		return toolTip;
	}
	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}
	public IModel getLinkImageAsModel(){
		return linkImage;
	}
	public String getLinkImage(){
		return (String) linkImage.getObject();
	}
	public String getName() {
		return (String) name.getObject();
	}
	public void setName(String name) {
		this.name = new Model(name);
	}

	public boolean isLinkEnabled(){
		return true;
	}
	public String getLinkVisiblePermits(){
		return null;
	}
	public String getLinkEnablePermits(){
		return null;
	}
	public boolean isLinkVisible(){
		return true;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IView){
			IView v=(IView)obj;
			return getName().equals(v.getName());
		}
		return false;
	}
}
