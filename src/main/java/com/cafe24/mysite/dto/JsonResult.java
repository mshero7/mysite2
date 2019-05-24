package com.cafe24.mysite.dto;

public class JsonResult {
	private String result; // success 나 fail 만을 가질 수 있음.
	private String message; // if fail, set
	private Object data; // if success, set

	public static JsonResult success(Object data) {
		return new JsonResult("success", null, data);
	}

	public static JsonResult fail(String message) {
		return new JsonResult("fail", message, null);
	}

	private JsonResult(String result,String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
	
}