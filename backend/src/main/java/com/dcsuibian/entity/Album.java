package com.dcsuibian.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Album {
    private Long id;
    private String title;
    private List<String> pictures;
    private Game game;
}
