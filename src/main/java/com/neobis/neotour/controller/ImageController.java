package com.neobis.neotour.controller;

import com.neobis.neotour.dto.ImageDto;
import com.neobis.neotour.service.CloudinaryService;
import com.neobis.neotour.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final CloudinaryService cloudinaryService;

    @GetMapping
    public ResponseEntity<List<ImageDto>> getAllImages() {
        return ResponseEntity.ok(imageService.getImages());
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<ImageDto> uploadImage(@RequestParam MultipartFile file) throws IOException {
        Map result = cloudinaryService.upload(file);
        String url = result.get("url").toString();
        String remoteId = result.get("public_id").toString();
        return ResponseEntity.ok(imageService.createImage(ImageDto.builder().url(url).remoteId(remoteId).build()));
    }

    @DeleteMapping(path = "/{imageId}")
    public ResponseEntity<String> deleteImageById(@PathVariable("imageId") Long id) throws IOException {
        ImageDto imageDto = imageService.getImageById(id);
        cloudinaryService.delete(imageDto.getRemoteId());
        imageService.deleteImageById(id);
        return ResponseEntity.ok("Image successfully deleted.");
    }

}
