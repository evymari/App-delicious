package com.f5.delicious.exceptions;

public class NoNameFoundException extends AppException {
  public NoNameFoundException(String name) {
    super("Creator with name " + name + " not found in data base");
  }
}
