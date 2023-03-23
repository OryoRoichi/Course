package by.home.Course.exceptions;

public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException(Long lessonId) {
        super(String.format("Урок с id = %d не найден",lessonId));
    }
}
