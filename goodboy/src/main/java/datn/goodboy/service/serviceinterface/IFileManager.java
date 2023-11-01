package datn.goodboy.service.serviceinterface;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IFileManager {
  public String saveImage(MultipartFile multipartFile) throws IOException;

  public File getFile(String name);

  public boolean deletedFile(String name);
}
