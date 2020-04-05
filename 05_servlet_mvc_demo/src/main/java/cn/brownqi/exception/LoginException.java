package cn.brownqi.exception;

public class LoginException extends RuntimeException {
    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "用户名或密码错误";
    }
}
