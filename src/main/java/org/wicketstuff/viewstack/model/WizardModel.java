package org.wicketstuff.viewstack.model;

import org.wicketstuff.viewstack.view.IView;
/**
 * 
 * @author fernando wermus
 *
 */
public class WizardModel extends ViewStackModel implements IWizardModel{
	private static final long serialVersionUID = -2997419897962817291L;

	public boolean hasNext() {
		return getActiveViewIndex() + 1 < size();
	}


	public boolean hasPrevious() {
		return 0 < getActiveViewIndex();
	}

	public IView next() {
		IView view=getView(getActiveViewIndex() + 1);
		if (view==null){
			// la vista no debe mostrarse buscamos la siguiente y debe existir
			view=getView(getActiveViewIndex() + 2);
		}
		setActiveView(view);
		return getActiveView();
	}

	public IView previous() {
		IView view=getView(getActiveViewIndex()  - 1);
		if (view==null){
			// la vista no debe mostrarse buscamos la anterior y debe existir
			view=getView(getActiveViewIndex()  - 2);
		}
		setActiveView(view);
		return getActiveView();
	}


	public boolean isFinish() {
		return !hasNext();
	}

}
