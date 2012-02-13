package com.admin.owlmanager;

public class Triplet {
	private String subject;
	private String predicate;
	private String object;
	
	public Triplet() { }
	
	public Triplet (String s, String p, String o) {
		this.subject = s;
		this.predicate = p;
		this.object = o;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPredicate() {
		return predicate;
	}

	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getSimpleSubgect() {
	
		return null;
	}
	
	public String getSimplePredicate() {
		
		return null;
	}

	public String getSimpleObject() {
	
		return null;
	}
}
