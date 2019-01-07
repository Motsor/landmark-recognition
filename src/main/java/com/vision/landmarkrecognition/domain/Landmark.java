package com.vision.landmarkrecognition.domain;

import com.google.type.LatLng;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Landmark {
    String description;
    LatLng coords;
    double score;

    public Landmark() {
    }

    public Landmark(String description, LatLng coords, double score) {
        this.description = description;
        this.coords = coords;
        this.score = score;
    }

}
