package C45;

import java.util.ArrayList;

public class JoinedColumTuple {
	
	private String target;
	private int attribute;

	public JoinedColumTuple(String target, int attribute) {

		this.target = target;
		this.attribute = attribute;

	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getAttribute() {
		return attribute;
	}

	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}

}
