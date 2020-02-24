package hr.martinovic.FakeStagram.controller;

import hr.martinovic.FakeStagram.model.*;
import hr.martinovic.FakeStagram.db.ImageRepository;
import hr.martinovic.FakeStagram.security.SecurityUtils;
import hr.martinovic.FakeStagram.utils.ImageUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("images/new")
public class ImageController {

    private Logger log = LoggerFactory.getLogger(ImageController.class);

    private final ImageRepository imageRepository;
    private final ImageUploadService imageUploadService;

    public ImageController(ImageRepository imageRepository, ImageUploadService imageUploadService) {
        this.imageRepository = imageRepository;
        this.imageUploadService = imageUploadService;
    }


    @GetMapping
    public String showNewImageForm(Model model){
        model.addAttribute("image", new ImageFile());
        model.addAttribute("imageTypes", Image.Type.values());
        return "newImageForm";
    }

    @PostMapping
    public String createNewImage(@RequestParam("file") MultipartFile file, Image image, Errors errors, Model model) throws IOException {
        imageUploadService.uploadFile(file);
        image.setPath("pics/" + file.getOriginalFilename());
        image.setOwner(SecurityUtils.getUsername());
        if(errors.hasErrors()){
            model.addAttribute("imageTypes", Image.Type.values());
            log.info("Errors found in the submitted form!");
            return "newImageForm";
        }
        imageRepository.save(image);
        String strType = image.getType().toString();
        if (strType.equals("ROTATED")){
            Context context = new Context(new RotateImage());
            log.info(context.executeStrategy(image.getName()));
        }
        if (strType.equals("RESIZED")){
            Context context = new Context(new ResizeImage());
            log.info(context.executeStrategy(image.getName()));
        }

        return "newImageUploaded";
    }

}
