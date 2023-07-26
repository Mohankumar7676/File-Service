package com.typeface.fileservice.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginatedResponse {
    
    private Integer limit;
    private Integer cursor;
    private Integer totalCursors;
    private Long totalFiles;

}
