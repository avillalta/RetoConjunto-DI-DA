package com.reto.dto;


import com.reto.enums.Format;
import com.reto.enums.Status;
import com.reto.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Movie movie;
    private Status status;
    private Format format;
}
