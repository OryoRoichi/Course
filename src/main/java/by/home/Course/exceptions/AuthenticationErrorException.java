package by.home.Course.exceptions;

public class AuthenticationErrorException extends  RuntimeException{
    public AuthenticationErrorException() {
        super("Ошибка авторизации");
    }
}
