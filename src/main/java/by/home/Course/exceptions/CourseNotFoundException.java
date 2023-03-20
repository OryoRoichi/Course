package by.home.Course.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long id) {
        super(String.format("Курс с id = %d не найден",id));
    }
}
