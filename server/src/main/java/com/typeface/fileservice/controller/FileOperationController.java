package com.typeface.fileservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.typeface.fileservice.models.Fileoperation;
import com.typeface.fileservice.models.dtos.FileRequest;
import com.typeface.fileservice.models.dtos.PaginatedFileResponse;
import com.typeface.fileservice.models.dtos.PaginatedResponse;
import com.typeface.fileservice.service.FileOperationsService;
import com.typeface.fileservice.utils.ResponseHandler;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping({ "/" })
@AllArgsConstructor
public class FileOperationController {

    @Autowired
    private FileOperationsService fileOperationsService;

    @CrossOrigin
    @PostMapping("/create-fileOperations")
    public void createfileOperations(@RequestBody Fileoperation requestBody) throws Exception {
        fileOperationsService.addFileOperation(requestBody);
    }

    @CrossOrigin
    @GetMapping("/get-fileOperations")
    public void getfileOperations(@RequestBody FileRequest requestBody) throws Exception {
        fileOperationsService.getFileOperations(requestBody);
    }

    @CrossOrigin
    @GetMapping("/getAll")
    public List<Fileoperation> getAll() throws Exception {
        return fileOperationsService.getAll();
    }
    
    @CrossOrigin
    @GetMapping(value = "/files")
    public ResponseEntity<Object> getFilesViaPagination(
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "cursor", required = false) Integer cursor,
            @RequestParam(value = "sortByFieldName", defaultValue = "createdAt", required = false) String sortByFieldName,
            @RequestParam(value = "sortOrder", defaultValue = "ASC", required = false) String sortOrder) throws Exception {
        
        Pair<List<PaginatedFileResponse>, PaginatedResponse> response = fileOperationsService.getFilesViaPagination(limit, cursor, sortByFieldName, sortOrder);
        return  new ResponseEntity<>(new ResponseHandler<>(response.getFirst(), response.getSecond()), HttpStatus.OK);
    }

}
