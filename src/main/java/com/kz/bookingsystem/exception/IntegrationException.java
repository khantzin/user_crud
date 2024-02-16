package com.kz.bookingsystem.exception;

/**
 * @author jameslwin
 * @createdAt - Mon Feb, 2020
 */
public class IntegrationException extends CoreApiException {
	private static final long serialVersionUID = 1L;

	public IntegrationException(String title, String message, String code) {
		super(title, message, code);
	}
	public IntegrationException(String message, String code) {
		super(message, code);
	}

}
