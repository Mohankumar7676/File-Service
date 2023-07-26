package com.typeface.fileservice.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import com.typeface.fileservice.models.Fileoperation;
import com.typeface.fileservice.repository.CustomFileRepository;

@Repository
public class CustomFileRepositoryImpl implements CustomFileRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Pair<Long, List<Fileoperation>> getFilesViaPagination(Integer limit, Integer cursor, String sortByFieldName, String sortOrder) throws Exception{
    
    Query query = new Query();
    long totalRecords = mongoTemplate.count(query, Fileoperation.class);

    if(limit != null){
        PageRequest pageRequest = PageRequest.of(cursor == null ? 0 : cursor-1, limit);
        query.with(pageRequest);
    }

    if(sortOrder.equals("ASC")){
        query.with(Sort.by(Sort.Direction.ASC, sortByFieldName));
    } else {
        query.with(Sort.by(Sort.Direction.DESC, sortByFieldName));
    }

    List<Fileoperation> files = mongoTemplate.find(query, Fileoperation.class);
    return Pair.of(totalRecords, files);
}
    
}
