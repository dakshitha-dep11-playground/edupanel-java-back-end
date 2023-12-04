package lk.ijse.dep11.edupanel.to.request;

import lk.ijse.dep11.edupanel.validation.LectureImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturerRequestTO {
    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid Name")
    private String name;
    @NotBlank(message = "Designation Cannot be blank")
    @Length(min = 2, message = "invalid designation")
    private String designation;
    @NotBlank(message = "Qualification cannot be blank")
    @Length(min = 2, message = "invalid qualification")
    private String qualifications;
    @Pattern(regexp = "^(full-time|part-time)$",flags = Pattern.Flag.CASE_INSENSITIVE, message = "Invalid type")
    @NotBlank(message = "Type cannot be blank")
    private String type;
    @LectureImage(maximumFileSize = 500*1024)
    private MultipartFile picture;
//    @Pattern(regexp = "^http[s]?://linkedin.com.+$",message = "Invalid linkedin")
    @URL
    private String linkedIn;
}
