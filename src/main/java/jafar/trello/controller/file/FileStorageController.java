package jafar.trello.controller.file;

import jafar.trello.dto.file.UploadsDto;
import jafar.trello.services.file.FileStorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.NoSuchFileException;

@Controller
@RequestMapping("uploads/*")
public class FileStorageController {

    final FileStorageService fileStorageService;

    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("{filename:.+}")
    public ResponseEntity<?> download(@PathVariable(name = "filename") String fileName) throws NoSuchFileException {
        UploadsDto loadedResource = fileStorageService.loadResource(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadedResource.getOriginalName() + "\"")
                .body(loadedResource.getResource());
    }

    @GetMapping("picture/{filename:.+}")
    public ResponseEntity<?> downloadpicture(@PathVariable(name = "filename") String fileName) throws NoSuchFileException {
        UploadsDto loadedResource = fileStorageService.loadResource(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadedResource.getOriginalName() + "\"")
                .body(loadedResource.getResource());
    }
}
