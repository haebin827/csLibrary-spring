package edu.library.libraryspring.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FaqDTO {

    private Integer f_id;       // int AI PK

    @NotEmpty
    private String f_question;  // varchar(255) NN

    @NotEmpty
    private String f_answer;    // varchar(1000) NN
}
