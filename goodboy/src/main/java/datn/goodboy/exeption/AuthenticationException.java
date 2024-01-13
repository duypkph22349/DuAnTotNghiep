package datn.goodboy.exeption;

public class AuthenticationException extends RuntimeException {
  public AuthenticationException(String message) {
    super(message);
  }

  public static class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException(String message) {
      super(message);
    }
  }
}
