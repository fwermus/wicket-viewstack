package org.wicketstuff.viewstack.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.WicketRuntimeException;
import org.wicketstuff.viewstack.statement.IPredicate;
import org.wicketstuff.viewstack.view.IView;

/**
 *
 * @author fernando wermus
 */
public class ViewStackModel implements  IViewStackModel{
	private static final long serialVersionUID = 4405725337743928616L;
	
	public static final String ACTIVE_VIEW = "activeView";
	protected List<IView> items;
	protected List<IPredicate> trueItems;
	
	private IView activeView;


	/**
	 * 
	 */
	public ViewStackModel()
	{	
		items = new ArrayList<IView>();
		trueItems = new ArrayList<IPredicate>();
	}

	public void add(int index, IView item)
	{
		items.add(index, item);
	}

	public IView getView(int index)
	{
		if (!trueItems.get(index).evaluate()){
			throw new WicketRuntimeException("you can't show a view that is hidden");
		}
//		if (trueItems.get(index).evaluate()){
		return getInternalView(index);
//		}else{
//			return null;
//		}
	}

	public IView getInternalView(int index) {
		return items.get(index);
	}
	
	public ViewStackModel setActiveView(IView activeView) {
		if (!items.contains(activeView) || !trueItems.get(items.indexOf(activeView)).evaluate()){
			throw new WicketRuntimeException("Attempt to set a default view that is not contained in the model");
		} 
		this.activeView = activeView;
		return this;
	}

	public Integer size(){
		return items.size();
	}

	public IView getObject() {
		return getActiveView();
	}
	public void setObject(IView object) {
		throw new UnsupportedOperationException();
	}

	public IView getActiveView() {
		if ((items.indexOf(activeView)==-1) || !trueItems.get(items.indexOf(activeView)).evaluate()){
			throw new WicketRuntimeException("you can't show this view");
		}
		return activeView;
	}
	
	public void detach() {
		
	}

	public void add(IView view) {
		items.add(view);
		trueItems.add(TruePredicate.getInstance());
		if (items.size()==1){
			setActiveView(view);
		}
	}
	public void add(IView view, IPredicate predicate) {
		items.add(view);
		trueItems.add(predicate);
		if (items.size()==1 && predicate.evaluate()){
			setActiveView(view);
		}
	}
	
	
	public void setObject(Object object) {
		// TODO Auto-generated method stub
		
	}

	public static ViewStackModel empty() {
		return new ViewStackModel();
	}

	public Integer getActiveViewIndex() {
		return items.indexOf(getActiveView());
		
	}

	public void setActiveView(int i) {
		activeView=items.get(i);
		setActiveView(activeView);
	}

	public List<IView> getItems() {
		return items;
	}
/**
 * Solucion de compromiso
 * FIXME
 */
//	public IViewStackModel filter() {
//		ViewStackModel vsm=new ViewStackModel();
//		 boolean setActiveView=false;
//		for (int i=0; i< items.size(); i++) {
//			if (trueItems.get(i).evaluate()){
//				vsm.add(items.get(i));
//			}else{
//				setActiveView=true;
//			}
//		}
//		if (setActiveView){
//			vsm.setActiveView(0);
//		}
//		return vsm;
//	}

	public boolean isVisible(IView view) {
		return trueItems.get(items.indexOf(view)).evaluate();
	}

public IView getFirstViewVisible() {
	Iterator<IPredicate> it= trueItems.iterator();
	boolean isTrue=false;
	int i=0;
	while (it.hasNext() && !isTrue) {
		isTrue=it.next().evaluate();
		i++;
	}
	return getView(i);
}






}
