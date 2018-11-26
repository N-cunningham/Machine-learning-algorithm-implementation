package C45;

import java.util.ArrayList;

public class JoinedColumTuple {
	
	private String target;
	private double attribute;

	public JoinedColumTuple(String target, double attribute) {

		this.target = target;
		this.attribute = attribute;

	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public double getAttribute() {
		return attribute;
	}

	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}

}
