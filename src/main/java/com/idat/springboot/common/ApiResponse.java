package com.idat.springboot.common;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta Standard de la API")
public class ApiResponse<T> {

    @Schema(description = "Código de respuesta de la API", example = "SUCCESS")
    private String responseCode;
    @Schema(description = "Mensaje de respuesta de la API", example = "Operación realizada exitosamente")
    private String responseMessage;
    @Schema(description = "Datos de la respuesta")
    private T data;
    

    public ApiResponse(String responseCode, String responseMessage, T data) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }


    public String getResponseCode() {
        return responseCode;
    }


    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }


    public String getResponseMessage() {
        return responseMessage;
    }


    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }


    public T getData() {
        return data;
    }


    public void setData(T data) {
        this.data = data;
    }

    
}
