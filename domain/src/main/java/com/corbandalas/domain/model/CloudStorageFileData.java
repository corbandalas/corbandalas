package com.corbandalas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CloudStorageFileData {
    private String fileName;
    private String mimeType;
    private InputStream inputStream;
    private int length;

}
