package org.school.exception;

public class RestException extends RuntimeException{

	private static final long serialVersionUID = -2422631025483051091L;

	private String errorCd;
	private String errorMsg;
	
	public RestException() {}
	
	public RestException(String errorCd, String errorMsg) {
		this.errorCd = errorCd;
		this.errorMsg = errorMsg;
	}
	
	public String getErrorCd() {
		return errorCd;
	}
	public void setErrorCd(String errorCd) {
		this.errorCd = errorCd;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
