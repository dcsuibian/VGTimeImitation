package com.dcsuibian.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
public class Game {
    @Id
    private Long id;

    private String name;

    private String developer;

    private String publisher;

    private String introduction;

    @Column(name="tags")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String __tags;
    // Jackson用的是Getter和Setter，不用担心
    public List<String> getTags(){
        return Util.stringToList(__tags);
    }
    public void setTags(List<String> tags){
        this.__tags=Util.listToString(tags);
    }

    @Column(name="genes")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String __genes;
    public List<String> getGenes(){
        return Util.stringToList(__genes);
    }
    public void setGenes(List<String> genes){
        this.__genes= Util.listToString(genes);
    }

    @Transient
    private List<Album> albums;
}
