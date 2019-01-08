package com.vision.landmarkrecognition.domain;

import com.google.type.LatLng;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Landmark implements Comparable<Landmark> {
    String description;
    LatLng coords;
    int score;

    public Landmark() {
    }

    public Landmark(String description, LatLng coords, int score) {
        this.description = description;
        this.coords = coords;
        this.score = score;
    }

    @Override
    public int compareTo(Landmark landmark) {
        return landmark.getScore() - this.score;
    }
}
