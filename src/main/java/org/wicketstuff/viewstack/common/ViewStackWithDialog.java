package org.wicketstuff.viewstack.common;

import java.lang.reflect.Constructor;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.MaskType;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.viewstack.model.ViewStackModel;
import org.wicketstuff.viewstack.view.IView;

/**
 * 
 * @author fernando wermus
 *
 */
public class ViewStackWithDialog extends Panel implements IViewStack{
	private static final long serialVersionUID = 1077489966175034472L;
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(ViewStackWithDialog.class);
	
	private ModalWindow dialog;
	private ModalWindowContentPage dialogPage;

	public ViewStackWithDialog(String id, IModel model) {
		this(id, model, ModalWindowContentPage.class);
	}
	
	@SuppressWarnings("unchecked")
	public ViewStackWithDialog(String id, IModel model, Class page){
		super(id, model);
		setOutputMarkupId(true);
		setDialog(new ModalWindow("dialog"));
		
// tamanio de widget formacion. Por ahora, todos los dialogos tienen el mismo tamanio.
		getDialog().setTitle("Dialogo");
		getDialog().setResizable(false); 
		getDialog().setInitialWidth(825); 
		getDialog().setInitialHeight(590); 
		getDialog().setWidthUnit("px"); 
		getDialog().setHeightUnit("px"); 
		getDialog().setCssClassName(ModalWindow.CSS_CLASS_GRAY);
		getDialog().setMaskType(MaskType.TRANSPARENT);
		add(getDialog());
		ModalWindowContentPage modalWebPage=null;
		try {
			Constructor constructor = page.getConstructor(new Class[]{IModel.class});
			modalWebPage = (ModalWindowContentPage)constructor.newInstance(model);
		} catch (Exception e) {
			modalWebPage=new ModalWindowContentPage(model);
			log.error(e);
		}
		setDialogPage(modalWebPage);
		
		
        getDialog().setPageCreator(new ModalWindow.PageCreator(){
			private static final long serialVersionUID = -8312779555190942673L;

			public Page createPage(){
				getDialogPage().setModel(ViewStackWithDialog.this.getModel());
                return getDialogPage();
            }
        });
        getDialog().setWindowClosedCallback(new ModalWindow.WindowClosedCallback(){
			private static final long serialVersionUID = -4005143294294907177L;

			public void onClose(AjaxRequestTarget target){
				if (!(getDialogPage().getPanel() instanceof IPopable)){
					return;
				}
            	IPopable r=(IPopable) getDialogPage().getPanel();
            	r.closed(target);
                
            }
        });

	}


/**
 *
 * @return
 */
	public String getView()
	{
		if (getModelObject() == null) {
			return "No view selected";
		} else {
			IView viewItem=(IView) getModelObject();
			return viewItem.getTitle();
		}
	}


/**
 *
 * @param viewItem
 */
	public void setSelectedView(IView viewItem)
	{
		getDialog().setContent((Component) viewItem.getView(getDialog().getContentId()));
	}

	public void refrescar(AjaxRequestTarget target) {
		getDialog().show(target);
	}
	public void close(AjaxRequestTarget target){
		getDialog().close(target);
	}
/* 
 * GETTERS & SETTERS
 */
	public ModalWindow getDialog() {
		return dialog;
	}
	public void setDialog(ModalWindow dialogo) {
		this.dialog = dialogo;
	}
	public ViewStackModel getViewStackModel() {
		return (ViewStackModel) getModelObject();
	}

	public ModalWindowContentPage getDialogPage() {
		return dialogPage;
	}
	private void setDialogPage(ModalWindowContentPage dialogPage) {
		this.dialogPage=dialogPage;
	}

	public Component getActiveView() {
		return getDialogPage().getPanel();
	}
}




