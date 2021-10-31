package com.dcsuibian.entity.po;

import com.dcsuibian.entity.Game;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static com.dcsuibian.util.Util.listToString;
import static com.dcsuibian.util.Util.stringToList;

@Getter
@Setter
@Entity(name = "game")
public class GamePO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String developer;
    private String publisher;
    private String introduction;
    private String tags;
    private String genes;

    public static GamePO convert(Game game) {
        if (null == game) return null;
        GamePO po = new GamePO();
        po.setId(game.getId());
        po.setName(game.getName());
        po.setDeveloper(game.getDeveloper());
        po.setPublisher(game.getPublisher());
        po.setIntroduction(game.getIntroduction());
        po.setTags(listToString(game.getTags()));
        po.setGenes(listToString(game.getGenes()));
        return po;
    }

    public static Game convert(GamePO po) {
        if (null == po) return null;
        Game game = new Game();
        game.setId(po.getId());
        game.setName(po.getName());
        game.setDeveloper(po.getDeveloper());
        game.setPublisher(po.getPublisher());
        game.setIntroduction(po.getIntroduction());
        game.setTags(stringToList(po.getTags()));
        game.setGenes(stringToList(po.getGenes()));
        return game;
    }
}
