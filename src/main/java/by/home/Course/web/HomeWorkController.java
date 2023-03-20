package by.home.Course.web;

import by.home.Course.entity.HomeWork;
import by.home.Course.entity.dto.HomeWorkDto;
import by.home.Course.entity.dto.LessonDto;
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
    @PostMapping("/create")
    public ResponseEntity<HomeWorkDto> createHomeWork(@RequestParam Long lessonId,@RequestBody HomeWorkDto request){
        return ResponseEntity.ok(homeWorkService.createHomeWork(lessonId,request));
    }



}
