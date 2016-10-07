package org.school.exception;

/**
 * RestException.
 * @author cdacr
 */
public final class RestException extends RuntimeException {

  /** Serial Version ID. */
  private static final long serialVersionUID = -2422631025483051091L;

  /** Error Code. */
  private String errorCd;
  /** Error Message. */
  private String errorMsg;

  /** RestException. */
  public RestException() {
  }

  /**
   * Parameterized constructor.
   * @param errorCd
   *          Error Code
   * @param errorMsg
   *          Error Message
   */
  public RestException(final String errorCd, final String errorMsg) {
    this.errorCd = errorCd;
    this.errorMsg = errorMsg;
  }

  /**
   * Get Error Code.
   * @return Error Code
   */
  public String getErrorCd() {
    return errorCd;
  }

  /**
   * Set Error Code.
   * @param errorCd
   *          Error Code
   */
  public void setErrorCd(final String errorCd) {
    this.errorCd = errorCd;
  }

  /**
   * Get Error Message.
   * @return Error Message
   */
  public String getErrorMsg() {
    return errorMsg;
  }

  /**
   * Set Error Message.
   * @param errorMsg
   *          Error Message
   */
  public void setErrorMsg(final String errorMsg) {
    this.errorMsg = errorMsg;
  }
}
