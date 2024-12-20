package com.f5.delicious.exceptions;

public class DuplicatePhoneException extends AppException {

  public DuplicatePhoneException(String phone) {
    super("Phone number " + phone + " already exists");
  }
}
