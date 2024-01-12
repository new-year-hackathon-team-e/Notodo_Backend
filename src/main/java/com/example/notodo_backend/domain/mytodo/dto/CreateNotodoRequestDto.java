package com.example.notodo_backend.domain.mytodo.dto;

import lombok.Data;

@Data
public class CreateNotodoRequestDto {
    private String content;

    private Boolean checkBox;
//    private CheckBox checkBox;




//    public CheckBox getCheckBox() {
//        return checkBox;
//    }

}
