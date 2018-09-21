package com.sinem.jumio.exception;

public class ExceptionResponse
{
    private String errorMessage;
    private String requestedURI;
    private int httpStatus;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRequestedURI() {
        return requestedURI;
    }

    public void callerURL(final String requestedURI) {
        this.requestedURI = requestedURI;
    }


    public int getHttpStatus()
    {
        return httpStatus;
    }


    public void setHttpStatus(int httpStatus)
    {
        this.httpStatus = httpStatus;
    }
}
