package datn.goodboy.service.cloud;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import datn.goodboy.service.serviceinterface.IFileManager;

@Service
public class CloudinaryImageService implements IFileManager {
  @Autowired
  Cloudinary config;

  @Override
  public String saveImage(MultipartFile multipartFile) throws IOException {
    return config.uploader()
        .upload(multipartFile.getBytes(),
            (Map.of("public_id", UUID.randomUUID().toString(), "folder", "DUANTOTNGHIEP")))
        .get("url")
        .toString();
  }

  @Override
  public File getFile(String name) {
    throw new UnsupportedOperationException("Unimplemented method 'getFile'");
  }

  @Override
  public boolean deletedFile(String name) {
    throw new UnsupportedOperationException("Unimplemented method 'deletedFile'");
  }

}
