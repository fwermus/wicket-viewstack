package org.wicketstuff.viewstack.model;

import java.io.Serializable;

import org.wicketstuff.viewstack.statement.IPredicate;
import org.wicketstuff.viewstack.view.IView;

/**
 * @author fernando wermus
 */
@SuppressWarnings("unchecked")
public interface IViewStackModel extends Serializable//extends IModel
{
	public IView getView(int i);
	public Integer size();
	public IViewStackModel setActiveView(IView viewItem);
	public IView getActiveView();
	public void add(IView view);
	public Integer getActiveViewIndex();
// FIXME solucion de compromiso. Revisar
//	public IViewStackModel filter();
	public IView getFirstViewVisible();
	public void add(IView view, IPredicate predicate);

}
