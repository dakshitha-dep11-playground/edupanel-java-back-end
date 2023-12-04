package lk.ijse.dep11.edupanel.api;

import lk.ijse.dep11.edupanel.to.request.LecturerRequestTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/lecturers")
public class LecturerHttpController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "multipart/form-data", produces = "application/json")
    public void createNewLecturer( @ModelAttribute @Valid LecturerRequestTO lecturer){
        System.out.println(lecturer);
        System.out.println("Save this lecturer");
    }

    @PatchMapping("/{lecturer-id}")
    public void updateLecturerDetails(){
        System.out.println("update lecturer");
    }

    @DeleteMapping("/{lecturer-id}")
    public void DeleteLecturer(){
        System.out.println("Delete lec");
    }

    @GetMapping
    public void getAllLecturers(){
        System.out.println("get all lec");
    }



}
