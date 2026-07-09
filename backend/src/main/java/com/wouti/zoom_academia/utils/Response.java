package com.wouti.zoom_academia.utils;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class Response<T>  implements Serializable{

	private T payload;
	private String message;
	private HttpStatus status;

	public Response() {
	}

	public Response(T resp,HttpStatus status, String message) {
		this.payload = resp;
		this.status = status;
		this.message = message;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public String getmessage() {
		return message;
	}

	public void setmessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
