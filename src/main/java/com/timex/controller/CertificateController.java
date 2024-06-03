package com.timex.controller;


import com.timex.model.Certificate;
import com.timex.service.CertificateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    //construction Injection
    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping("/save") //http://localhost:9494/certificate/save(give in form data)
    public ResponseEntity<?> addCertificate(@Valid @ModelAttribute Certificate certificate,
                                            @RequestParam("file") MultipartFile file) {
        try {
            certificateService.addCertificate(certificate, file);
            return ResponseEntity.ok("Your certificate is successfully uploaded");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading certificate: " + e.getMessage());
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id) {
        byte[] fileName = certificateService.downloadFile(id);
        if (fileName == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileName);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCertificate(@PathVariable Long id) {
        boolean deleted = certificateService.deleteCertificate(id);
        if (deleted) {
            return ResponseEntity.ok("Certificate deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Certificate not found");
        }
    }
}
