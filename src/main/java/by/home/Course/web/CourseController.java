package by.home.Course.web;

import by.home.Course.entity.dto.CourseDto;
import by.home.Course.entity.dto.HomeWorkReviewDto;
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

}
