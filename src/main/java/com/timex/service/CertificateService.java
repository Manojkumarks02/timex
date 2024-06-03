package com.timex.service;

import com.timex.model.Certificate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CertificateService {

    void addCertificate(Certificate certificate, MultipartFile file) throws IOException;

    byte[] downloadFile(Long id);


    boolean deleteCertificate(Long id);
}
