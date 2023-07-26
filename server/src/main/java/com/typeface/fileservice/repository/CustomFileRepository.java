package com.typeface.fileservice.repository;

import java.util.List;

import org.springframework.data.util.Pair;

import com.typeface.fileservice.models.Fileoperation;

public interface CustomFileRepository {
    Pair<Long, List<Fileoperation>> getFilesViaPagination(Integer limit, Integer cursor, String sortBy, String sortOrder) throws Exception;
}