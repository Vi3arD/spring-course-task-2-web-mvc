package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemAlreadyIsDeletedException extends RuntimeException {
  public ItemAlreadyIsDeletedException() {
  }

  public ItemAlreadyIsDeletedException(String message) {
    super(message);
  }

  public ItemAlreadyIsDeletedException(String message, Throwable cause) {
    super(message, cause);
  }

  public ItemAlreadyIsDeletedException(Throwable cause) {
    super(cause);
  }

  public ItemAlreadyIsDeletedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
