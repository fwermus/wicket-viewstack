package org.wicketstuff.viewstack.model;

import org.wicketstuff.viewstack.view.IView;
/**
 * 
 * @author fernando wermus
 *
 */
public interface IWizardModel extends IViewStackModel {
	public IView previous();
	public boolean hasPrevious();
	public IView next();
	public boolean hasNext();
	public boolean isFinish();
}
