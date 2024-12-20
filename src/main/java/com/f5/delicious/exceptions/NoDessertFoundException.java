package com.f5.delicious.exceptions;

public class NoDessertFoundException extends RuntimeException {
  public NoDessertFoundException (Long guardianId) {
    super("No pets found for guardian with ID: " + guardianId);
  }
}