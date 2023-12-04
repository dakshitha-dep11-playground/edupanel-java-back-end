package lk.ijse.dep11.edupanel.api;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/lecturers")
public class LecturerHttpController {

    @PostMapping
    public void createNewLecturer(){
        System.out.println("create new");
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
