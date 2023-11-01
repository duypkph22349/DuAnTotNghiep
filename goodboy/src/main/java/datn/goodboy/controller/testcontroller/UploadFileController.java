package datn.goodboy.controller.testcontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import datn.goodboy.service.ImageService;
import datn.goodboy.service.cloud.CloudinaryImageService;

/**
 * UploadFileController
 */
@Controller
@RequestMapping("test/postfile")
public class UploadFileController {
  @Autowired
  CloudinaryImageService imageService;
  @Autowired
  ImageService imageSv;

  @GetMapping("getform")
  public String uploadFile() {
    return "test/uploadfile.html";
  }

  @PostMapping("uploadfile")
  public String SaveFile(Model model, @RequestParam("imageFile") List<MultipartFile> file) throws IOException {
    List<String> listURL = new ArrayList<>();
    for (MultipartFile multipartFile : file) {
      String url = imageService.saveImage(multipartFile);
      listURL.add(url);
    }
    imageSv.saveImageForNewProductDetail(listURL, 9);
    return "test/uploadfile.html";
  }
}