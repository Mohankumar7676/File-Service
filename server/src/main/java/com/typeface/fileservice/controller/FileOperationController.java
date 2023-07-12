


package com.typeface.fileservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.typeface.fileservice.models.Fileoperation;
import com.typeface.fileservice.models.dtos.FileRequest;
import com.typeface.fileservice.service.FileOperationsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping({"/"})
@AllArgsConstructor
public class FileOperationController {
    
    @Autowired
    private FileOperationsService fileOperationsService;

    @CrossOrigin
    @PostMapping("/create-fileOperations")
    public void createfileOperations( @RequestBody Fileoperation requestBody) throws Exception {
        fileOperationsService.addFileOperation(requestBody);
    }

    @CrossOrigin
    @GetMapping("/get-fileOperations")
    public void getfileOperations( @RequestBody FileRequest requestBody) throws Exception {
        fileOperationsService.getFileOperations(requestBody);
    }

    @CrossOrigin
    @GetMapping("/getAll")
    public List<Fileoperation> getAll() throws Exception {
        return fileOperationsService.getAll();
    }

}
