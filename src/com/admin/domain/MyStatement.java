package com.admin.domain;

public class MyStatement implements Comparable<MyStatement> {
	
	private static final String URL_SEP_CHAR = "/";
	private static final String NAME_SEP_CHAR = "#";
	
	private String statement;
	private String cleanStatement;
	
	public MyStatement() {}
	
	public MyStatement(String value) {
		this.statement = value;
		this.setCleanStatement(statement);
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
	
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
		this.setCleanStatement(statement);
	}
	
	private void setCleanStatement(String cleanStatement) {
		if (this.statement.contains(NAME_SEP_CHAR)) {
			this.cleanStatement = this.getLastToken(statement, NAME_SEP_CHAR);
		} else {
			this.cleanStatement = this.getLastToken(statement, URL_SEP_CHAR);
		}
	}
	
	public String getCleanStatement() {
		return cleanStatement;
	}
	
	@Override
	public String toString() {
		return this.statement;
	}

	@Override
	public int compareTo(MyStatement anotherStatement) {	
		return this.cleanStatement.compareTo(anotherStatement.getCleanStatement());
	}
}
