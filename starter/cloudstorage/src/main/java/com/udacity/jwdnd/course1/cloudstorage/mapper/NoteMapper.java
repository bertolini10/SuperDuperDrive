package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    Note getNote(Integer userid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{username}, #{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insert(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}  ")
    int delete(Note note);

    @Update("UPDATE NOTES SET notetitle = #{notetitle}  , notedescription = #{notedescription} WHERE noteid = #{noteid} ")
    int update(Note note);
}
