package com.kodigo.airportapp.response;


import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Setter
@Getter
public class ResponseApi<T> implements Serializable {

  private Boolean success;
  private String message;
  private T data;

  public ResponseApi() {

  }

  public ResponseApi(T data) {
    this.data = data;
  }

  public ResponseApi(Boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public ResponseApi(Boolean success, String message, T data) {
    this.success = success;
    this.message = message;
    this.data = data;
  }

  public ResponseApi(Boolean success, T data) {
    this.success = success;
    this.data = data;
  }
}
