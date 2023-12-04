package lk.ijse.dep11.edupanel.api;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/lecturers/full-time")
public class FullTimeLecturerHttpController {

    @PatchMapping("/ranks")
    public void arrangeFullTimeLecturerOrder(){
        System.out.println("arrange full time");
    }

    @GetMapping
    public void getAllFulLTimeLecturerOrder(){
        System.out.println("get All part time");
    }
}
