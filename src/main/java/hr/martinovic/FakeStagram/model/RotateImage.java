package hr.martinovic.FakeStagram.model;

import hr.martinovic.FakeStagram.utils.Strategy;

public class RotateImage implements Strategy {
    @Override
    public String editImage(String name) {
        return "Image " + name + " was rotated";
    }
}
