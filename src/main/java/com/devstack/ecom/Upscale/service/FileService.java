package com.devstack.ecom.Upscale.service;

import com.devstack.ecom.Upscale.util.CommonFileSavedBinaryDataDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public CommonFileSavedBinaryDataDto create(MultipartFile file, String directory, String bucket);
    public void delete(String fileName, String directory, String bucket);
    public byte[] downloadFile(String bucket, String fileName);
}
