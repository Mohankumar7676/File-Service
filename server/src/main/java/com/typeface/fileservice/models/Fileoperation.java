package com.typeface.fileservice.models;



import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("File_Operations")
public class Fileoperation {
    
    private String id;
    private String fileName;
    private Operations operations;

}
