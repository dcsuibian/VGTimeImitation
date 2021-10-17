package com.dcsuibian.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Game {
    private Long id;
    private String name;
    private String developer;
    private String publisher;
    private String introduction;
    private List<String> tags;
    private List<String> genes;
    @JsonManagedReference
    private List<Album> albums;
}
