package com.dcsuibian.entity.po;

import com.dcsuibian.entity.Album;
import com.dcsuibian.entity.Game;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.dcsuibian.util.Util.stringToList;
import static com.dcsuibian.util.Util.listToString;

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

    public static AlbumPO convert(Album album) {
        if (null == album) return null;
        AlbumPO po = new AlbumPO();
        po.setId(po.getId());
        po.setTitle(po.getTitle());
        po.setPictures(listToString(album.getPictures()));
        po.setGameId(null == album.getGame() ? null : album.getGame().getId());
        return po;
    }

    public static Album convert(AlbumPO po) {
        if (null == po) return null;
        Album album = new Album();
        album.setId(po.getId());
        album.setTitle(po.getTitle());
        album.setPictures(stringToList(po.getPictures()));
        if (null != po.getGameId()) {
            Game game = new Game();
            game.setId(po.getGameId());
            album.setGame(game);
        }
        return album;
    }
}
