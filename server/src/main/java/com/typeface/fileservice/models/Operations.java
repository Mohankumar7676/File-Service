package com.typeface.fileservice.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operations {
    
    private List<Comments> comments;
    private List<OtherOperations> others;
}
