package datn.goodboy.model.request;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserProductFillter {
  List<Integer> brandfil;

  List<Integer> materialfil;

  List<Integer> originfil;

  List<Integer> pattenfil;

  List<Integer> stylesfil;

}
