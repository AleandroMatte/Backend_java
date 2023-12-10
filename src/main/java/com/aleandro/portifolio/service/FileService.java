package com.aleandro.portifolio.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;

import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import javax.swing.filechooser.FileNameExtensionFilter;

@Service
public class FileService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public String generatePreSignedUrl(String filePath, HttpMethod http) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE,2);
        return amazonS3.generatePresignedUrl(bucketName,filePath,cal.getTime(),http).toString();
    }
    
    public ResponseEntity<String> Post_image_in_bucket(MultipartFile image, String url) throws IOException, URISyntaxException, InterruptedException {
       
        String jsonPayload = "{\"key\": \"value\"}";


        HttpClient client = HttpClient.newHttpClient();
    
        byte[] imageBytes = image.getBytes();
        String ext1 = FilenameUtils.getExtension(image.getOriginalFilename()); // returns "txt"
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "image/png")
                .PUT(HttpRequest.BodyPublishers.ofByteArray(imageBytes))
                .build();

        HttpResponse<String> response;
		response = client.send(request, HttpResponse.BodyHandlers.ofString());


  
        int statusCode = response.statusCode();
        String responseBody = response.body();
        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);
		return ResponseEntity.ok("USER IMAGE UPLOADED");

  
    }
}
