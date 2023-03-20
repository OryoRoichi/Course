package by.home.Course.web;

import by.home.Course.entity.Course;
import by.home.Course.entity.dto.CourseDto;
import by.home.Course.entity.dto.HomeWorkViewDto;
import by.home.Course.service.CourseService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CourseController {
    CourseService courseService;

    @RolesAllowed("ROLE_MENTOR")
    @PostMapping("/create")
    public ResponseEntity<CourseDto> createCource(@RequestBody CourseDto request){
        return ResponseEntity.ok(courseService.createCource(request));
    }
    @RolesAllowed("ROLE_MENTOR")
    @PostMapping("/check")
    public ResponseEntity<HomeWorkViewDto> homeWorkCheck(@RequestParam Long lessonId, @RequestParam Long courseId){
        return ResponseEntity.ok(courseService.checkHomeWork(lessonId,courseId));
    }
}
