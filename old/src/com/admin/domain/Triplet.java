package com.admin.domain;

/**
 * This is an abstraction class to manage
 * triplets as strings in an easier way
 * 
 * @author israelord
 */
public class Triplet {
	
	
	
	private MyStatement subject;
	private MyStatement predicate;
	private MyStatement object;
	
	public Triplet() { }
	
	public Triplet (String s, String p, String o) {
		this.subject = new MyStatement(s);
		this.predicate = new MyStatement(p);
		this.object = new MyStatement(o);
	}

	/******************************************************************
	 *                  GETTERS AND SETTERS                           * 
	 ******************************************************************/
	
	public String getSubject() {
		return subject.getStatement();
	}

	public void setSubject(String subject) {
		this.subject.setStatement(subject);
	}

	public String getPredicate() {
		return predicate.getStatement();
	}

	public void setPredicate(String predicate) {
		this.predicate.setStatement(predicate);
	}

	public String getObject() {
		return object.getStatement();
	}

	public void setObject(String object) {
		this.object.setStatement(object);
	}

	
	
	/*************************************************************
	 *        Simple getters, cleans the statements and          * 
	 *         returns only the part of the string that          *
	 *            matters to understand the statement            *
	 *************************************************************/
	
	public String getSimpleSubject() {
		return this.subject.getCleanStatement();
	}
	
	public String getSimplePredicate() {
		return this.predicate.getCleanStatement();
	}

	public String getSimpleObject() {
		return this.object.getCleanStatement();
	}
	
	@Override
	public String toString() {
		return "{ " + subject + "  " + predicate + "  " + object + " }";
	}
}
