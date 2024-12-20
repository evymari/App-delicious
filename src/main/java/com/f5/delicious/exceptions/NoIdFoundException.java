package com.f5.delicious.exceptions;

public class NoIdFoundException extends AppException {

  public NoIdFoundException(Long id) {
    super("Entity with ID " + id + " not found in data base");
  }
}

