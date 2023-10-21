package datn.goodboy.validate.voucher;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = StartBeforeEndValidator.class)
@Target({ ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface StartBeforeEnd {
  String message() default "start_time must be before end_time";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}