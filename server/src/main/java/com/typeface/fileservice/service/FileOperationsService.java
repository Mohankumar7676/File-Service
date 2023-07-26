package com.typeface.fileservice.service;

import java.util.List;

import org.springframework.data.util.Pair;

import com.typeface.fileservice.models.Fileoperation;
import com.typeface.fileservice.models.dtos.FileRequest;
import com.typeface.fileservice.models.dtos.PaginatedFileResponse;
import com.typeface.fileservice.models.dtos.PaginatedResponse;

public interface FileOperationsService {
    
    Fileoperation addFileOperation(Fileoperation operation);
    List<Fileoperation> getFileOperations(FileRequest fileName);
    List<Fileoperation> getAll();
        Pair<List<PaginatedFileResponse>, PaginatedResponse> getFilesViaPagination(Integer limit, Integer cursor, String sortBy, String sortOrder)
            throws Exception;
}
