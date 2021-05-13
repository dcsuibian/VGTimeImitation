package com.dcsuibian.dao;

import com.dcsuibian.domain.Topic;
import com.dcsuibian.domain.User;
import com.openhtmltopdf.css.parser.property.PrimitivePropertyBuilders;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ITopicDao {
    @Insert("insert into topic(editor_id,author_id,content,type,title,resume,status,modify_time,change_time,cover_image) values(#{editor.id},#{author.id},#{content},#{type},#{title},#{resume},#{status},#{modifyTime},#{changeTime},#{coverImage})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void add(Topic topic);//增操作

    @Select("select * from topic where editor_id=#{id}")
    @Results(id="TopicMap",value = {
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "content",property = "content"),
            @Result(column = "type",property = "type"),
            @Result(column = "title",property = "title"),
            @Result(column = "resume",property = "resume"),
            @Result(column = "status",property = "status"),
            @Result(column = "modify_time",property = "modifyTime"),
            @Result(column = "change_time",property = "changeTime"),
            @Result(column = "cover_image",property = "coverImage"),
            @Result(property = "author",column = "author_id",one = @One(select = "com.dcsuibian.dao.IUserDao.findById",fetchType = FetchType.EAGER)),
            @Result(property = "editor",column = "editor_id",one = @One(select = "com.dcsuibian.dao.IUserDao.findById",fetchType = FetchType.EAGER)),
    })
    List<Topic> findByEditor(User user);

    @Update("UPDATE topic SET editor_id=#{editor.id},author_id=#{author.id},content=#{content},type=#{type},title=#{title},resume=#{resume},`status`=#{status},modify_time=#{modifyTime},cover_image=#{coverImage} WHERE id=#{id}")
    void save(Topic topic);//其实是update操作



    @Insert("insert into topic(editor_id,author_id,content,type,title,resume,status,modify_time,change_time,cover_image) values(#{editor.id},#{author.id},#{content},#{type},#{title},#{resume},#{status},#{modifyTime},#{changeTime},#{coverImage})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insert(Topic topic);//增

    @Delete("DELETE FROM topic WHERE id=#{id}")
    void delete(long id);//删

    @Update("UPDATE topic SET editor_id=#{editor.id},author_id=#{author.id},content=#{content},type=#{type},title=#{title},resume=#{resume},`status`=#{status},modify_time=#{modifyTime},change_time=#{changeTime},cover_image=#{coverImage} WHERE id=#{id}")
    void update(Topic topic);//改

    @Select("select * from topic where id=#{id}")
    @Results(id = "topicMap",value = {
            @Result(column = "modify_time",property = "modifyTime"),
            @Result(column = "change_time",property = "changeTime"),
            @Result(column = "cover_image",property = "coverImage"),
            @Result(column = "author_id",property = "author",one = @One(select = "com.dcsuibian.dao.IUserDao.findById",fetchType = FetchType.EAGER)),
            @Result(column = "editor_id",property = "editor",one = @One(select = "com.dcsuibian.dao.IUserDao.findById",fetchType = FetchType.EAGER)),
    })
    Topic findById(long id);


    @Select(value = {
            " <script> "+
                    " SELECT * FROM topic "+
                    " <where> 1=1 "+
                    " <if test=\"null!=author\"> AND author_id=#{author.id}</if> "+
                    " <if test=\"null!=editor\"> AND editor_id=#{editor.id}</if> "+
                    " <if test=\"null!=type\"> AND type=#{type}</if> "+
                    " <if test=\"null!=status\"> AND status=#{status}</if> "+
                    " <if test=\"null!=queryStr\"> AND ( title LIKE CONCAT('%',#{queryStr},'%') OR resume LIKE CONCAT('%',#{queryStr},'%') OR content LIKE CONCAT('%',#{queryStr},'%'))</if> "+
                    " <if test=\"null!=orderBy\"> ORDER BY ${orderBy}</if> "+
                    " <if test=\"null!=limit\"> LIMIT <if test=\"null!=offset\">#{offset},</if>#{limit}</if> "+
                    " </where> "+
                    " </script> "
    })
    @ResultMap("topicMap")
    List<Topic> queryByConditions(@Param("author") User author,@Param("editor") User editor,@Param("type") String type,@Param("status") String status,@Param("queryStr") String queryStr,@Param("orderBy") String orderBy,@Param("offset") Long offset,@Param("limit") Long limit);

    @Select(value = {
            " <script> "+
                    " SELECT COUNT(*) FROM topic "+
                    " <where> 1=1 "+
                    " <if test=\"null!=author\"> AND author_id=#{author.id}</if> "+
                    " <if test=\"null!=editor\"> AND editor_id=#{editor.id}</if> "+
                    " <if test=\"null!=type\"> AND type=#{type}</if> "+
                    " <if test=\"null!=status\"> AND status=#{status}</if> "+
                    " <if test=\"null!=queryStr\"> AND ( title LIKE CONCAT('%',#{queryStr},'%') OR resume LIKE CONCAT('%',#{queryStr},'%') OR content LIKE CONCAT('%',#{queryStr},'%'))</if> "+
                    " </where> "+
                    " </script> "
    })
    long queryCountByConditions(@Param("author") User author,@Param("editor") User editor,@Param("type") String type,@Param("status") String status,@Param("queryStr") String queryStr);

        @Select("select * from topic")
    @ResultMap("topicMap")
    List<Topic> queryAll();
//
//    @Select("select * from topic")
//    @ResultMap("topicMap")
//    List<Topic> queryForAuditor();
}
