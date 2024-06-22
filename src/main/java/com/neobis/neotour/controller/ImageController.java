package com.neobis.neotour.controller;

import com.neobis.neotour.dto.ImageDto;
import com.neobis.neotour.service.CloudinaryService;
import com.neobis.neotour.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all images", description = "Returns a list of all uploaded images")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Images successfully retrieved")
    })
    @GetMapping
    public ResponseEntity<List<ImageDto>> getAllImages() {
        return ResponseEntity.ok(imageService.getImages());
    }

    @Operation(summary = "Upload an image", description = "Uploads the image to Cloudinary and saves details in DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image successfully uploaded")
    })
    @PostMapping(path = "/upload")
    public ResponseEntity<ImageDto> uploadImage(@RequestParam MultipartFile file) throws IOException {
        Map result = cloudinaryService.upload(file);
        String url = result.get("url").toString();
        String remoteId = result.get("public_id").toString();
        return ResponseEntity.ok(imageService.createImage(ImageDto.builder().url(url).remoteId(remoteId).build()));
    }

    @Operation(summary = "Delete an image by id", description = "Deletes the image from Cloudinary and DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Image id not found")
    })
    @DeleteMapping(path = "/{imageId}")
    public ResponseEntity<String> deleteImageById(@PathVariable("imageId") Long id) throws IOException {
        ImageDto imageDto = imageService.getImageById(id);
        cloudinaryService.delete(imageDto.getRemoteId());
        imageService.deleteImageById(id);
        return ResponseEntity.ok("Image successfully deleted.");
    }

}
