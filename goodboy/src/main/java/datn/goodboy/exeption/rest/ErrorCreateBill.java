package datn.goodboy.exeption.rest;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ErrorCreateBill extends RuntimeException {
  public ErrorCreateBill() {
    super();
  }

  public ErrorCreateBill(String message) {
    super(message);
  }

  public ErrorCreateBill(String message, Throwable throwable) {
    super(message, throwable);
  }

  public ErrorCreateBill(Throwable cause) {
    super(cause);
  }
}
