package by.home.Course.web;

import by.home.Course.entity.dto.HomeWorkDto;
import by.home.Course.entity.dto.HomeWorkReviewDto;
import by.home.Course.entity.dto.LessonDto;
import by.home.Course.service.LessonService;
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

    LessonService lessonService;

    @RolesAllowed("ROLE_MENTOR")
    @PostMapping("/lesson")
    public ResponseEntity<LessonDto> createLesson(@RequestBody LessonDto request) {
        return ResponseEntity.ok(workFlowService.createLesson( request));
    }
    @RolesAllowed("ROLE_STUDENT")
    @PostMapping("/homework")
    public ResponseEntity<HomeWorkDto> createHomeWork(@RequestBody HomeWorkDto request){
        return ResponseEntity.ok(workFlowService.createHomeWork(request));
    }
    @RolesAllowed("ROLE_MENTOR")
    @PostMapping("/review")
    public ResponseEntity<HomeWorkDto> giveReview(@RequestBody HomeWorkDto request){
        return ResponseEntity.ok(workFlowService.setReview(request));
    }

}
