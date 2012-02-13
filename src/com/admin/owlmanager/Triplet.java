package com.admin.owlmanager;

/**
 * This is an abstraction class to manage
 * triplets as strings in an easier way
 * 
 * @author israelord
 */
public class Triplet {
	
	private static final String URL_SEP_CHAR = "/";
	private static final String NAME_SEP_CHAR = "#";
	
	private String subject;
	private String predicate;
	private String object;
	
	public Triplet() { }
	
	public Triplet (String s, String p, String o) {
		this.subject = s;
		this.predicate = p;
		this.object = o;
	}

	/******************************************************************
	 *                  GETTERS AND SETTERS                           * 
	 ******************************************************************/
	
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

	/**
	 * Gets the last token, normally a subject, predicate or object statement
	 * has the following form: http://url/ontology-name#subject for example.
	 * 
	 * @param complete the complete statement
	 * @param sepChar the character to split the statement ( / or # )
	 * @return the latest part of the statement
	 */
	private String getLastToken(String complete, String sepChar) {
		String[] tokens = complete.split(sepChar);
		return tokens[tokens.length - 1];
	}
	
	/*************************************************************
	 *        Simple getters, cleans the statements and          * 
	 *         returns only the part of the string that          *
	 *            matters to understand the statement            *
	 *************************************************************/
	
	public String getSimpleSubject() {
		String result = null;
		
		if (subject.contains(NAME_SEP_CHAR)) {
			result = getLastToken(subject, NAME_SEP_CHAR);
		} else {
			result = getLastToken(subject, URL_SEP_CHAR);
		}
		
		return result;
	}
	
	public String getSimplePredicate() {
		String result = null;
		
		if (predicate.contains(NAME_SEP_CHAR)) {
			result = getLastToken(predicate, NAME_SEP_CHAR);
		} else {
			result = getLastToken(predicate, URL_SEP_CHAR);
		}
			
		return result;
	}

	public String getSimpleObject() {
		String result = null;
		
		if (object.contains(NAME_SEP_CHAR)) {
			result = getLastToken(object, NAME_SEP_CHAR);
		} else {
			result = getLastToken(object, URL_SEP_CHAR);
		}
		
		return result;
	}
}
