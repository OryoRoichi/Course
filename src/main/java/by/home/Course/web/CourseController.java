package by.home.Course.web;

import by.home.Course.entity.dto.CourseDto;
import by.home.Course.entity.dto.HomeWorkReviewDto;
import by.home.Course.service.CourseService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestParam Long studentId, @RequestParam Long courseId){
        return ResponseEntity.ok(courseService.addStudent(studentId,courseId));
    }

   // @RolesAllowed({"ROLE_MENTOR", "ROLE_STUDENT"})
    @PreAuthorize("hasAnyRole('ROLE_MENTOR','ROLE_STUDENT')")
    @GetMapping("/view")
    public ResponseEntity<CourseDto> viewCourse(@RequestParam Long courseId){
        return ResponseEntity.ok(courseService.viewCourse(courseId));
    }

    @RolesAllowed("ROLE_MENTOR")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCourse(@RequestParam Long courseId){
        return ResponseEntity.ok(courseService.deleteCourse(courseId));
    }

}
