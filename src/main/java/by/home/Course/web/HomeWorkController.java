package by.home.Course.web;

import by.home.Course.entity.HomeWork;
import by.home.Course.entity.dto.HomeWorkDto;
import by.home.Course.entity.dto.HomeWorkReviewDto;
import by.home.Course.entity.dto.LessonDto;
import by.home.Course.entity.dto.UncheckedHomeworkDto;
import by.home.Course.service.HomeWorkService;
import by.home.Course.service.LessonService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/homework")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class HomeWorkController {
    HomeWorkService homeWorkService;

    @RolesAllowed("ROLE_STUDENT")
    @GetMapping("/view_homework")
    public ResponseEntity<HomeWorkReviewDto> viewStudentsHomeWork(@RequestParam Long lessonId){
        return ResponseEntity.ok(homeWorkService.viewStudentsHomeWork(lessonId));
    }

    @RolesAllowed("ROLE_MENTOR")
    @GetMapping("/view_unchecked")
    public ResponseEntity<UncheckedHomeworkDto> viewUncheckedHomeWork(@RequestParam Long lessonId){
        return ResponseEntity.ok(homeWorkService.viewUncheckedHomeWork(lessonId));
    }

}
