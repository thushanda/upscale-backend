package com.devstack.ecom.Upscale.util;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;

@Service
public class FileDataExtractor {

    public byte[] blobToByteArray(Blob blob) throws Exception{
        if (blob==null){
            return new byte[0];
        }else {
            try(InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
                byte[] buffer = new byte[4096];
                int byteRead;
                while ((byteRead=inputStream.read(buffer)) != -1){
                    outputStream.write(buffer,0,byteRead);
                }
                return outputStream.toByteArray();
            }
        }
    }

    public String byteArrayToString(byte[] byteArray){
        if (byteArray == null || byteArray.length == 0){
            return null;
        }
        return new String(byteArray, StandardCharsets.UTF_8);
    }

    public String extractActualFileName(InputStreamReader streamReader) throws Exception{
        try {
            StringBuffer stringBuffer = new StringBuffer();
            String temp;
            BufferedReader bufferedReader = null;
            bufferedReader = new BufferedReader(streamReader);
        while ((temp = bufferedReader.readLine()) != null){
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
