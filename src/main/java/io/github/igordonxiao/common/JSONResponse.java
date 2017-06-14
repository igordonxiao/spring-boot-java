package io.github.igordonxiao.common;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Business Logic Response Object
 */
public final class JSONResponse implements Serializable {
    private static final long serialVersionUID = 1013123223232L;
    private static final JSONResponse DEFAULT_OK = OK(HttpStatus.OK);

    /**
     * response something to client with status and message
     *
     * @param status
     * @param message
     */
    public JSONResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    /**
     * response something to client with status, message, and data
     *
     * @param status
     * @param message
     * @param data
     */
    public JSONResponse(HttpStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * Response Status
     */
    private HttpStatus status;

    /**
     * Business Logic Response Message
     */
    private String message;

    /**
     * Business Logic Response Object
     */
    private Object data;

    /**
     * response successfully with message
     *
     * @return
     */
    public static JSONResponse OK() {
        return DEFAULT_OK;
    }


    /**
     * response successfully with message
     *
     * @param message
     * @param data
     * @return
     */
    public static JSONResponse OK(String message, Object data) {
        return new JSONResponse(HttpStatus.OK, message, data);
    }

    /**
     * response successfully with default message and custom object
     *
     * @param data
     * @return
     */
    public static JSONResponse OK(Object data) {
        return new JSONResponse(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), data);
    }

    /**
     * response error with message
     *
     * @param status
     * @return
     */
    private static JSONResponse ERROR(HttpStatus status) {
        return new JSONResponse(status, status.getReasonPhrase());
    }

    /**
     * response error with bad param
     *
     * @return
     */
    public static JSONResponse ERROR_FOR_BAD_PARAM() {
        return ERROR(HttpStatus.BAD_REQUEST);
    }

    /**
     * response error with not found
     *
     * @return
     */
    public static JSONResponse ERROR_FOR_NOT_FOUND() {
        return ERROR(HttpStatus.NOT_FOUND);
    }

    /**
     * response error with server error
     *
     * @return
     */
    public static JSONResponse ERROR_FOR_SERVER_ERROR() {
        return ERROR(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public Integer getStatus() {
        return status.value();
    }

    public HttpStatus getStatusOriginal() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}