package com.reto.model;

import com.reto.enums.Format;
import com.reto.enums.Status;
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
