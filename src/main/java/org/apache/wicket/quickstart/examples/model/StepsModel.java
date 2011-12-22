package org.apache.wicket.quickstart.examples.model;

import java.io.Serializable;

public class StepsModel implements Serializable {
	private static final long serialVersionUID = 2592196950239622745L;
	
	private String textField1;
	private String textField2;
	private String textField3;
	
	public String getTextField1() {
		return textField1;
	}
	public void setTextField1(String textField1) {
		this.textField1 = textField1;
	}
	public String getTextField2() {
		return textField2;
	}
	public void setTextField2(String textField2) {
		this.textField2 = textField2;
	}
	public String getTextField3() {
		return textField3;
	}
	public void setTextField3(String textField3) {
		this.textField3 = textField3;
	}
	
}
