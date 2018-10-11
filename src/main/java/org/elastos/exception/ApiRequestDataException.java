/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.exception;

public class ApiRequestDataException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public ApiRequestDataException() {
        super();
    }
    public ApiRequestDataException(String message) {
        super(message);
    }
    
    public ApiRequestDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiRequestDataException(Throwable cause) {
        super(cause);
    }

    protected ApiRequestDataException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
