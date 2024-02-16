package com.kz.bookingsystem.handler;

import com.kz.bookingsystem.utils.JsonUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResponseSuccessHandler {
	private String code = "200";
	private String message = "Success";
	private Object body;
	
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
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
	
	public ResponseEntity<String> response() {
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");

		/*if(null == this.body ){
			return new ResponseEntity<>(JsonUtil.prettyJSON(this.body), header, HttpStatus.NOT_FOUND);
		}*/
		return new ResponseEntity<>(JsonUtil.prettyJSON(this.body), header, HttpStatus.OK);
	}

}
