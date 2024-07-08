package com.devstack.ecom.Upscale.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonFileSavedBinaryDataDto {

    private Blob hash;
    private String directory;
    private Blob fileName;
    private Blob resourceUrl;
}
