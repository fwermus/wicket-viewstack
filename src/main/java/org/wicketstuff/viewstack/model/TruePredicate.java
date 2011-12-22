package org.wicketstuff.viewstack.model;

import org.wicketstuff.viewstack.statement.IPredicate;

public class TruePredicate implements IPredicate {
	private static final long serialVersionUID = -3464485325855436673L;
	private static IPredicate truePredicate= new TruePredicate();

	public boolean evaluate() {
		return true;
	}

	public static IPredicate getInstance() {
		return truePredicate;
	}

}
