package edu.library.libraryspring.domain;
/*
    classname : NotificationVO (client read only)
    creation date : 2024-07-16
    updated date : 2024-7-20 (Added isImp field)
    remarks :
 */
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationVO {

    private Integer n_no;              // int AI PK
    private String n_title;            // varchar(100) NN
    private String n_content;          // varchar(255) NN
    private Boolean n_isImp;           // boolean default NULL
    private LocalDate n_regDate;   // timestamp NN default CURRENT_TIMESTAMP
    private LocalDate n_modDate;   // timestamp default NULL on UPDATE CURRENT_TIMESTAMP
}
