package com.f5.delicious.exceptions;

public class NoIdFoundBadRequestException extends AppException {


  public NoIdFoundBadRequestException(Long id) {
    super("The provided ID " + id + " does not match any existing creator records");
  }
}
