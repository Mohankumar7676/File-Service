package com.typeface.fileservice.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.typeface.fileservice.models.Fileoperation;


@Repository
public interface FileRepository extends MongoRepository<Fileoperation, String> {
    
    @Query("{'fileName': ?0}")
    List<Fileoperation> findByFileName(String fileName);
    
}