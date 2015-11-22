package com.labbooktool.synchronize.model;

public class RestResponseModel {

	private Object data;
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	private int code;

	public static RestResponseModel createSuccessResponse(Object data) {
		
		return new RestResponseModel(data,200);
	}

	public RestResponseModel(Object data, int code) {
		super();
		this.data = data;
		this.code = code;
	}
	

}
