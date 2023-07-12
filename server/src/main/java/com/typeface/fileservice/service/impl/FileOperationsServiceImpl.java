package com.typeface.fileservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.typeface.fileservice.models.Fileoperation;
import com.typeface.fileservice.models.dtos.FileRequest;
import com.typeface.fileservice.repository.FileRepository;
import com.typeface.fileservice.service.FileOperationsService;

@Service
public class FileOperationsServiceImpl implements FileOperationsService {

    private @Autowired FileRepository fileRepository;
    @Override
    public Fileoperation addFileOperation(Fileoperation operation) {
        List<Fileoperation> isExists = fileRepository.findByFileName(operation.getFileName());
        if(isExists.size()>0){
            operation.setId(isExists.get(0).getId()); 
        }
        return fileRepository.save(operation);
    }

    @Override
    public List<Fileoperation> getFileOperations(FileRequest data) {
        return fileRepository.findByFileName(data.getFilename());
    }

    @Override
    public List<Fileoperation> getAll() {
        return fileRepository.findAll();
    }
    
}
