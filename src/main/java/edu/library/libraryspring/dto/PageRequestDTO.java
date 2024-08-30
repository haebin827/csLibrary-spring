package edu.library.libraryspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 20000)
    @Positive
    private int size = 10;

    private String link;

    private String[] types;

    private String keyword;

    private String keyword2;

    private LocalDate from;

    private LocalDate to;

    public int getSkip() {
        return (page - 1) * 10;
    }

    public String getLink() {
        StringBuilder builder = new StringBuilder();

        builder.append("page=" + this.page);

        builder.append("&size=" + this.size);

        // Check and append types
        boolean hasTypes = types != null && types.length > 0;
        if (hasTypes) {
            for (String type : types) {
                builder.append("&types=").append(type);
            }
        }

        // Append keyword only if types exist
        if (hasTypes && keyword != null && !keyword.isEmpty()) {
            try {
                builder.append("&keyword=").append(URLEncoder.encode(keyword, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if(keyword2 != null && !keyword.isEmpty()){
            try {
                builder.append("&keyword2=" + URLEncoder.encode(keyword2,"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if(from != null){
            builder.append("&from=" + from.toString());
        }

        if(to != null){
            builder.append("&to=" + to.toString());
        }

        return builder.toString();
    }

    public boolean checkType(String type) {

        if(types == null || types.length == 0) {
            return false;
        }
        return Arrays.stream(types).anyMatch(type::equals);
    }
}
