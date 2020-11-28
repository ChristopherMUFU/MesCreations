package Entities;

import java.io.Serializable;

public abstract class Entity implements Serializable {
	
	protected String representation;
	
	public String getRepresentation() {
		return this.representation;
	}
	
	public void setRepresentation(String rep) {
		this.representation = rep;
	}
	
}
