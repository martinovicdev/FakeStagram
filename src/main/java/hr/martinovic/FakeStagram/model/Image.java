package hr.martinovic.FakeStagram.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotEmpty(message = "{validation.image.name.notEmpty}")
    @Size(min = 3, max = 100, message = "{validation.image.name.size}")
    private String name;

    private LocalDate date;

    @NotNull(message = "{validation.image.fakeness.notNull}")
    @DecimalMin(value="0.01", message = "{validation.image.fakeness.decimalMin}")
    @DecimalMax(value = "10.01", message = "{validation.image.fakeness.decimalMax}")
    private Double fakeness;

    private String path;

    private String owner;


    @NotNull(message = "validation.image.type.notNull")
    private Type type;

    public String getPath() {
        return path;
    }

    public void setPath(String file) {
        this.path = file;
    }

    public enum Type {
        NORMAL, ROTATED, RESIZED
    }

    public Image(){
        this.date = LocalDate.now();
    }

    public Image(String name, Double fakeness, Type type, String path, String owner) {
        this.name = name;
        this.date = LocalDate.now();
        this.type = type;
        this.fakeness = fakeness;
        this.path = path;
        this.owner = owner;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getFakeness() { return fakeness; }

    public void setFakeness(Double fakeness) { this.fakeness = fakeness; }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
