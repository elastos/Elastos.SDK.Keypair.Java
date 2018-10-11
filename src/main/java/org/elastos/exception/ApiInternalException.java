/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.exception;

public class ApiInternalException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public ApiInternalException() {
        super();
    }
    public ApiInternalException(String message) {
        super(message);
    }
    
    public ApiInternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiInternalException(Throwable cause) {
        super(cause);
    }

    protected ApiInternalException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
