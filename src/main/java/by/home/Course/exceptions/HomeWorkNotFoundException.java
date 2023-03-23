package by.home.Course.exceptions;

public class HomeWorkNotFoundException extends RuntimeException{
    public HomeWorkNotFoundException(Long homeWorkId) {
        super(String.format("Домашняя работа с id = %d не найдена",homeWorkId));
    }
}
