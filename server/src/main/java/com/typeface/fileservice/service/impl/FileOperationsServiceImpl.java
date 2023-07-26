package com.typeface.fileservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.typeface.fileservice.models.Fileoperation;
import com.typeface.fileservice.models.dtos.FileRequest;
import com.typeface.fileservice.models.dtos.PaginatedFileResponse;
import com.typeface.fileservice.models.dtos.PaginatedResponse;
import com.typeface.fileservice.repository.CustomFileRepository;
import com.typeface.fileservice.repository.FileRepository;
import com.typeface.fileservice.service.FileOperationsService;

@Service
public class FileOperationsServiceImpl implements FileOperationsService {

    private @Autowired FileRepository fileRepository;
    private @Autowired CustomFileRepository customFileRepository;
    private @Autowired ModelMapper modelMapper;
    
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

        @Override
    public Pair<List<PaginatedFileResponse>, PaginatedResponse> getFilesViaPagination(Integer limit, Integer cursor, String sortBy,
            String sortOrder) throws Exception {

        Pair<Long, List<Fileoperation>> files = customFileRepository
                                                .getFilesViaPagination(limit, cursor, sortBy, sortOrder);
        Integer totalCursors = null;
        if(limit != null){
            totalCursors = (int) Math.ceil((double) files.getFirst() / limit);
        } 

        PaginatedResponse pageResponse = new PaginatedResponse(limit,cursor,totalCursors,files.getFirst());
        List<PaginatedFileResponse> customResponse = files.getSecond()
                .stream()
                .map(file -> modelMapper.map(file, PaginatedFileResponse.class))
                .collect(Collectors.toList());
        return Pair.of(customResponse,pageResponse);
    }
    
}
