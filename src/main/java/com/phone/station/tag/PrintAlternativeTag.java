package com.phone.station.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.mockito.cglib.core.GeneratorStrategy;

public class PrintAlternativeTag extends SimpleTagSupport{

	private boolean ifTrue;
	private String out;
	private String otherwise;

	public void setIfTrue(boolean ifTrue) {
		this.ifTrue = ifTrue;
	}

	public void setOut(String out) {
		this.out = out;
	}

	public void setOtherwise(String otherwise) {
		this.otherwise = otherwise;
	}

	@Override
	public void doTag() throws JspException, IOException {
			String toPrint = ifTrue ? out : otherwise;
			getJspContext().getOut().print(toPrint);
	}

}
