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
        return landmark.getScore() - score;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Landmark)) {
            return false;
        }

        return description.equals(((Landmark) o).getDescription());
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + description.hashCode();
        return hash;
    }

}
