package com.vision.landmarkrecognition.service;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import com.vision.landmarkrecognition.domain.Landmark;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageAnalyzer {

    public List<Landmark> detectLandmark(String filePath) throws IOException {
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ByteString imgBytes;
        try (FileInputStream fis = new FileInputStream(filePath)) {
            imgBytes = ByteString.readFrom(fis);
        }

        Image img = Image.newBuilder().setContent(imgBytes).build();

        Feature feature = Feature.newBuilder().setType(Feature.Type.LANDMARK_DETECTION).build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feature).setImage(img).build();
        requests.add(request);

        List<Landmark> landmarkList = new ArrayList<>();

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);

            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    return null;
                }
                for (EntityAnnotation annotation : res.getLandmarkAnnotationsList()) {

                    LocationInfo info = annotation.getLocationsList().listIterator().next();

                    landmarkList.add(new Landmark(annotation.getDescription(), info.getLatLng(), annotation.getScore()));

                    if (landmarkList.size() == 3) {
                        break;
                    }
                }
            }
        }

        return landmarkList;
    }
}
