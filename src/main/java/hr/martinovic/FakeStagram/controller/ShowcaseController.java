package hr.martinovic.FakeStagram.controller;

import hr.martinovic.FakeStagram.model.Image;
import hr.martinovic.FakeStagram.db.ImageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/images")
public class ShowcaseController {
    private final ImageRepository imageRepository;

    public ShowcaseController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @GetMapping
        public String showcase(Model model){
        Iterable<Image> imglist = imageRepository.findAll();
        model.addAttribute("imglist", imglist);
        return "images";
        }
    }

