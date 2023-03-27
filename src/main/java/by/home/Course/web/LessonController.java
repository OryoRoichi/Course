package by.home.Course.web;

import by.home.Course.entity.dto.HomeWorkReviewDto;
import by.home.Course.entity.dto.LessonDto;
import by.home.Course.service.LessonService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class LessonController{
    LessonService lessonService;
}
