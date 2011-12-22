package org.wicketstuff.viewstack.common;

import org.apache.wicket.ajax.AjaxRequestTarget;
/**
 * Estaria bueno que pueda distinguir entre un cancel y accept.
 * El tema es que lo estamos haciendo en ViewStackWithDialog cuando se llama a cerrar la pagina
 * y no podemos saber cual fue la operacion realizada. Para que el refrescar funcione esta es la unica manera que encontre
 * @author fernando wermus
 * 
 * LEER CON ATENCION
 *
 */
public interface IPopable {


	void closed(AjaxRequestTarget target);

}
