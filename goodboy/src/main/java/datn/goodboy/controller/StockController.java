package datn.goodboy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "user")
public class StockController {
  
    @GetMapping(value = "index")
    public ModelMap mmDashboard() {
        return new ModelMap();
<<<<<<< HEAD
    } 
=======
    }
>>>>>>> 4910e2f486bfd3cf157ffe4f529c246ca1b2134f
}
