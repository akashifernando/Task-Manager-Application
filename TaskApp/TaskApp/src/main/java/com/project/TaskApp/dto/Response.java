package com.project.TaskApp.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
//generic class return all respons
// //data tranfer clean
@Data
@Builder //access the class build method without having new instance create an object
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
//
    private int statusCode;
    private String message;
    private T data;//actual paylaod to return
//gives API reply  same simple envelope—status code, message, data
}
