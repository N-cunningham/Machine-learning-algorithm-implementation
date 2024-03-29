package C45;

import java.util.ArrayList;

public class JoinedColumTuple {
	
	private String target;
	private double attribute;
	private int attributeNumber;

	public int getAttributeNumber() {
		return attributeNumber;
	}

	public void setAttributeNumber(int attributeNumber) {
		this.attributeNumber = attributeNumber;
	}

	public JoinedColumTuple(String target, double attribute, int attributeNumber) {

		this.target = target;
		this.attribute = attribute;
		this.attributeNumber = attributeNumber;

	}

	@Override
	public String toString() {
		return "JoinedColumTuple [target=" + target + ", attribute=" + attribute + "]";
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
