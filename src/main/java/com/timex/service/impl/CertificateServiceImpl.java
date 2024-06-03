package com.timex.service.impl;

import com.timex.model.Certificate;
import com.timex.repository.CertificateRepository;
import com.timex.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;


    @Override
    public void addCertificate(Certificate certificate, MultipartFile file) throws IOException {
        byte[] fileData = file.getBytes();
        certificate.setFileData(fileData);
        certificateRepository.save(certificate);
    }

    @Override
    public byte[] downloadFile(Long id) {
        Optional<Certificate> optionalFileEntity = certificateRepository.findById(id);
        if (optionalFileEntity.isPresent()) {
            return optionalFileEntity.get().getFileData();
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteCertificate(Long id) {
        Optional<Certificate> optionalCertificate = certificateRepository.findById(id);
        if (optionalCertificate.isPresent()) {
            certificateRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
