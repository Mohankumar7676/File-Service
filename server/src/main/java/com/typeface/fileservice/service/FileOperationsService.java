package com.typeface.fileservice.service;

import java.util.List;

import com.typeface.fileservice.models.Fileoperation;
import com.typeface.fileservice.models.dtos.FileRequest;

public interface FileOperationsService {
    
    Fileoperation addFileOperation(Fileoperation operation);
    List<Fileoperation> getFileOperations(FileRequest fileName);
    List<Fileoperation> getAll();
}
