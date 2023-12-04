package lk.ijse.dep11.edupanel.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LecturerImageConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LectureImage {

    Class<? extends Payload>[] payload() default {};

    String message() default "Invalid file size, over {maximumFileSize}";

    Class<?>[] groups() default {};

    long maximumFileSize() default 2500*1024;

}
