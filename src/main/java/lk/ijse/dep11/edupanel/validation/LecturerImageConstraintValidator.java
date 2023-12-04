package lk.ijse.dep11.edupanel.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LecturerImageConstraintValidator implements ConstraintValidator<LectureImage, MultipartFile > {
    private long maximumFileSize;
    @Override
    public void initialize(LectureImage constraintAnnotation) {
        maximumFileSize = constraintAnnotation.maximumFileSize();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null || multipartFile.isEmpty())return true;
        if(multipartFile.getSize() >maximumFileSize)return false;
        if (multipartFile.getContentType() == null)return false;
//        if (multipartFile.getContentType().startsWith("image/")) return false;
        return true;
    }
}
