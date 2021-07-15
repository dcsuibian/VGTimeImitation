package com.dcsuibian.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
public class Album {
    @Id
    private Long id;
    private String title;
    @Column("pictures")
    private String __pictures;

    public List<String> getPictures(){
        return Util.stringToList(__pictures);
    }
    public void setPictures(List<String> pictures){
        this.__pictures=Util.listToString(pictures);
    }
}
