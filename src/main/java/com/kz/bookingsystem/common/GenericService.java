package com.kz.bookingsystem.common;


import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;


public class GenericService<T> {
	private T data;
	private boolean status;
	private String code;
	private String message;
	private Throwable throwable;

	public GenericService() {

	}

	public GenericService(T datum, boolean status, String code, String message, Throwable throwable) {
		super();
		this.data = datum;
		this.status = status;
		this.code = code;
		this.message = message;
		this.throwable = throwable;
	}

	public T getData() {
		return data;
	}

	public void setData(T datum) {
		this.data = datum;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public void success(T datum) {
		this.data = datum;
		this.status = Boolean.TRUE;
		this.message = "success";
		this.code = "0000";
		this.throwable = null;
	}

	public void fail(Throwable throwable, String message) {
		this.data = null;
		this.status = Boolean.FALSE;
		this.message = message;
		this.throwable = throwable;
		
		if (throwable instanceof SQLException) {
			SQLException sqlException = (SQLException) throwable;
			this.code = "" + sqlException.getErrorCode();
		} else if (throwable instanceof EmptyResultDataAccessException) {
			this.code = "SQL_001";
		} else {
			this.code = "SYS_001";
		}
	}
	
	public void fail(Throwable throwable, String message, String code) {
		this.data = null;
		this.status = Boolean.FALSE;
		this.message = message;
		this.throwable = throwable;
		this.code = code;
	}

}
