package com.typeface.fileservice.models;

import java.util.Date;

import com.typeface.fileservice.models.enums.Types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherOperations {
    
    private Types type;
    private String change;
    private String user;
    private Date date;

}
