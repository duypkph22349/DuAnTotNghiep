package datn.goodboy.controller.testcontroller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import datn.goodboy.service.cloud.CloudinaryImageService;

/**
 * UploadFileController
 */
@Controller
@RequestMapping("test/postfile")
public class UploadFileController {
  @Autowired
  CloudinaryImageService imageService;

  @GetMapping("getform")
  public String uploadFile() {
    return "test/uploadfile.html";
  }

  @PostMapping("uploadfile")
  public String SaveFile(Model model,  @RequestParam("imageFile") MultipartFile file) throws IOException {
    String url = imageService.saveImage(file);
    model.addAttribute("filename", url);
    return "test/uploadfile.html";
  }
}