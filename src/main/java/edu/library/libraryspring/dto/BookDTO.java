package edu.library.libraryspring.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Integer b_no;       // int AI PK

    @NotEmpty
    private String b_isbn;      // varchar(10) NN

    @NotEmpty
    private String b_title;     // varchar(255) NN

    private String b_author;    // varchar(255) default NULL

    private Integer b_year;     // int default NULL

    private String b_publisher; // varchar(255) default NULL

    @NotNull
    private boolean isRental;   // tinyint(1) NN default '0'

    @NotNull
    private boolean isActive;   // tinyint(1) NN default '1'

    private String b_category;  // varchar(255) default NULL
}
