package org.wicketstuff.viewstack.statement;

import java.io.Serializable;

public interface IPredicate extends Serializable{
	public boolean evaluate();
}
