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
    @Column(name="genes")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String __genes;

    public List<String> getTags(){
        return stringToList(__tags);
    }
    public void setTags(List<String> tags){
        this.__tags=listToString(tags);
    }
    public List<String> getGenes(){
        return stringToList(__genes);
    }
    public void setGenes(List<String> genes){
        this.__genes= listToString(genes);
    }

    private String listToString(List<String> list){
        if(null==list||0==list.size()){
            return null;
        }
        String result="";
        for(var item:list){
            if(item.length()>=1&&-1==item.indexOf("|")){
                result+="".equals(result)?item:"|"+item;
            }else{
                throw new RuntimeException("不支持的类型");
            }
        }
        return result;
    }
    private List<String> stringToList(String s){
        return new ArrayList<>(Arrays.asList(s.split("\\|")));
    }
}
