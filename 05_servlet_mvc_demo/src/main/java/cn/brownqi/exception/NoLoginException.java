package cn.brownqi.exception;

public class NoLoginException extends RuntimeException {
    public NoLoginException() {
        super();
    }

    public NoLoginException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "未登录";
    }
}
