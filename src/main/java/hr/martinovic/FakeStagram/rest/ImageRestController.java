package hr.martinovic.FakeStagram.rest;

import hr.martinovic.FakeStagram.model.Image;
import hr.martinovic.FakeStagram.db.ImageRepository;
import hr.martinovic.FakeStagram.security.SecurityUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/image", produces = "application/json")
@Secured("ROLE_USER")
public class ImageRestController {

    private final ImageRepository imageRepository;

    public ImageRestController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @GetMapping
    public Iterable<Image> findAll(){
        return SecurityUtils.isAdmin() ? imageRepository.findAll() : imageRepository.findAllByOwner(SecurityUtils.getUsername());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Image> findOne(@PathVariable Long id){
        Optional<Image> image = SecurityUtils.isAdmin() ? imageRepository.findById(id) : imageRepository.findByIdAndOwner(id, SecurityUtils.getUsername());
        return image.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes="application/json")
    public Image save(@Valid @RequestBody Image image) {
        image.setOwner(SecurityUtils.getUsername());
        image.setDate(LocalDate.now());
        return imageRepository.save(image);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        boolean exists = SecurityUtils.isAdmin() ? imageRepository.existsById(id) : imageRepository.existsByIdAndOwner(id, SecurityUtils.getUsername());
        if(exists){
            imageRepository.deleteById(id);
        }
    }

    @PutMapping(value = "/{id}", consumes="application/json")
    public ResponseEntity<Image> update(@PathVariable Long id, @Valid @RequestBody Image updatedImage){
        Optional<Image> image = SecurityUtils.isAdmin() ? imageRepository.findById(id) : imageRepository.findByIdAndOwner(id, SecurityUtils.getUsername());

        image.ifPresent(value -> {
            value.setName(updatedImage.getName());
            value.setFakeness(updatedImage.getFakeness());
            value.setType(updatedImage.getType());
            value.setPath(updatedImage.getPath());
            
            imageRepository.save(value);
        });

        return image.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
