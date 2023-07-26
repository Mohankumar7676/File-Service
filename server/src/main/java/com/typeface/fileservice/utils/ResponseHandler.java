package com.typeface.fileservice.utils;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseHandler<T> {
	Boolean success;
    Date date = new Date();
	T data;
    T pagination;

	public ResponseHandler(T data) {
		this.success = true;
		this.data = data;
	}

	public ResponseHandler(Boolean success, T data, T pagination) {
		this(data);
        this.pagination = pagination;
		this.success = success;
	}

    public ResponseHandler(T data, T pagination) {
		this(data);
        this.pagination = pagination;
	}

}
