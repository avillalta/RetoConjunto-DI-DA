package model;

import enums.Format;
import enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieCopy {
    private Integer copyId;
    private Integer filmId;
    private Integer userId;
    private Status status;
    private Format format;
}
