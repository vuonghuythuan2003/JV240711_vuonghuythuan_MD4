package ra.jv240711_vuonghuythuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.jv240711_vuonghuythuan.service.UploadFileService;

@RestController
@RequestMapping("/api/v1/upload")
public class UpdateFileController {

    private final UploadFileService uploadFileService;

    @Autowired
    public UpdateFileController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @PostMapping("")
    public ResponseEntity<String> upload(@RequestParam MultipartFile file) {
        String fileName = uploadFileService.uploadFile(file);
        return new ResponseEntity<>(fileName, HttpStatus.CREATED);
    }
}