package by.home.Course.exceptions;
public class UserNotFoundException  extends  RuntimeException{
    public UserNotFoundException(Long studentId) {
        super(String.format("Пользователь с id = %d не найден",studentId));
    }
}
