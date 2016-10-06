package org.school.exception;

public final class RestException extends RuntimeException {

  private static final long serialVersionUID = -2422631025483051091L;

  private String errorCd;
  private String errorMsg;

  public RestException() {
  }

  public RestException(final String errorCd, final String errorMsg) {
    this.errorCd = errorCd;
    this.errorMsg = errorMsg;
  }

  public String getErrorCd() {
    return errorCd;
  }

  public void setErrorCd(final String errorCd) {
    this.errorCd = errorCd;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(final String errorMsg) {
    this.errorMsg = errorMsg;
  }
}
