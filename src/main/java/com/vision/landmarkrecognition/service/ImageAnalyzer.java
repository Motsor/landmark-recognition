package com.vision.landmarkrecognition.service;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import com.vision.landmarkrecognition.domain.Landmark;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ImageAnalyzer {

    public List<Landmark> detectLandmark(MultipartFile file) throws IOException {
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ByteString imgBytes;
        try (InputStream is = file.getInputStream()) {
            imgBytes = ByteString.readFrom(is);
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

                    landmarkList.add(new Landmark(annotation.getDescription(), info.getLatLng(), (int) (annotation.getScore() * 100)));

                    if (landmarkList.size() == 3) {
                        break;
                    }
                }
            }
        }
        return removeDuplicateAndSort(landmarkList);
    }

    private List<Landmark> removeDuplicateAndSort(List<Landmark> landmarkList) {
        int n = landmarkList.size();
        if (n > 1) {
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    if (landmarkList.get(i).getDescription().equals(landmarkList.get(j).getDescription())) {
                        if (landmarkList.get(i).getScore() >= landmarkList.get(j).getScore()) {
                            landmarkList.remove(j);
                            n--;
                        } else {
                            landmarkList.remove(j);
                        }
                    }
                }
            }
        }

        Collections.sort(landmarkList);
        return landmarkList;
    }
}
