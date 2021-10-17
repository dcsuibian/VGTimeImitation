package com.dcsuibian.entity.po;

import com.dcsuibian.entity.Album;
import com.dcsuibian.entity.Game;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "album")
public class AlbumPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "pictures")
    private String pictures;
    private Long gameId;

    public static Album convert(AlbumPO po) {
        Album album = new Album();
        album.setId(po.getId());
        album.setTitle(po.getTitle());
        album.setPictures(Util.stringToList(po.getPictures()));
        Game game = new Game();
        game.setId(po.getGameId());
        album.setGame(game);
        return album;
    }

    public static AlbumPO convert(Album album) {
        AlbumPO po = new AlbumPO();
        po.setId(po.getId());
        po.setTitle(po.getTitle());
        po.setPictures(Util.listToString(album.getPictures()));
        po.setGameId(null == album.getGame() ? null : album.getGame().getId());
        return po;
    }
}
