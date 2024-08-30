package edu.library.libraryspring.domain;
/*
    classname : FaqVO (client read only)
    creation date : 2024-07-16
    updated date :
    remarks :
 */
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaqVO {

    private Integer f_id;           // int AI PK
    private String f_question;  // varchar(255) NN
    private String f_answer;    // varchar(1000) NN
}
