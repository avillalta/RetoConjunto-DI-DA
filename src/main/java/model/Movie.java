package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private Integer id;
    private String title;
    private String Genre;
    private Integer year;
    private String description;
    private String director;

    public String toString(){
        return title;
    }
}
