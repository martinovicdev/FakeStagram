package hr.martinovic.FakeStagram.model;

import org.springframework.web.multipart.MultipartFile;

public class ImageFile extends Image {
    MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
