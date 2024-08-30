package edu.library.libraryspring.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private Integer n_no;          // int AI PK

    @NotEmpty
    private String n_title;        // varchar(100) NN

    @NotEmpty
    private String n_content;      // varchar(255) NN

    private Boolean n_isImp;       // boolean default NULL

    private LocalDate n_regDate;   // timestamp NN default

    private LocalDate n_modDate;   // timestamp default NULL on UPDATE CURRENT_TIMESTAMP
}
