package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
    File getFile(Integer fileId);

    @Select("SELECT * FROM FILES WHERE userid = #{userId} and filename = #{filename} ")
    File getFileByUserAndName(Integer userId,String filename);


    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<File> getAllFilesbyUser(Integer userid);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int insert(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid}  ")
    int delete(File file);

    @Update("UPDATE FILES SET filename = #{filename}  , contenttype = #{contenttype}, filesize = #{filesize}, filedata = #{filedata}  WHERE fileid = #{fileid} ")
    int update(File file);

}


