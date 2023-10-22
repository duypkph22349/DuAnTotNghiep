//package datn.goodboy.validate.voucher;
//
//import datn.goodboy.model.request.VoucherRequest;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//public class StartBeforeEndValidator implements ConstraintValidator<StartBeforeEnd, VoucherRequest> {
//
//  @Override
//  public void initialize(StartBeforeEnd constraintAnnotation) {
//  }
//
//  @Override
//  public boolean isValid(VoucherRequest voucher, ConstraintValidatorContext context) {
//    if (voucher == null) {
//      return true; // Let other annotations handle null checks (e.g., @NotNull)
//    }
//    return voucher.getStart_time().isBefore(voucher.getEnd_time());
//  }
//}
