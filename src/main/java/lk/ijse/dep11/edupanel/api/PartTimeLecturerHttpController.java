package lk.ijse.dep11.edupanel.api;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/lecturers/part-time")
public class PartTimeLecturerHttpController {
    @PatchMapping("/ranks")
    public void arrangePartTimeLecturerOrder(){
        System.out.println("arrage Part time");
    }

    @GetMapping
    public void getAllPartTimeLecturerOrder(){
        System.out.println("get All part time");
    }}
