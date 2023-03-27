package by.home.Course.web;

import by.home.Course.entity.dto.HomeWorkDto;
import by.home.Course.entity.dto.HomeWorkReviewDto;
import by.home.Course.entity.dto.LessonDto;
import by.home.Course.service.WorkFlowService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/workflow")
public class WorkFlowController {
    WorkFlowService workFlowService;

    @RolesAllowed("ROLE_MENTOR")
    @RequestMapping("/lesson")
    public ResponseEntity<LessonDto> createLesson(@RequestParam Long courseId, @RequestBody LessonDto request) {
        return ResponseEntity.ok(workFlowService.createLesson(courseId, request));
    }
    @RolesAllowed("ROLE_STUDENT")
    @RequestMapping("/homework")
    public ResponseEntity<HomeWorkDto> createHomeWork(@RequestParam Long lessonId, @RequestBody HomeWorkDto request){
        return ResponseEntity.ok(workFlowService.createHomeWork(lessonId,request));
    }
    @RolesAllowed("ROLE_MENTOR")
    @PostMapping("/review")
    public ResponseEntity<String> giveReview(@RequestBody HomeWorkReviewDto request){
        return ResponseEntity.ok(workFlowService.setReview(request));
    }

}
