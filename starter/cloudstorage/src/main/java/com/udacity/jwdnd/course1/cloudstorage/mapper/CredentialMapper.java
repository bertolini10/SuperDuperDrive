package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    Credential getCredential(Integer credentialid);

    @Insert("INSERT INTO CREDENTIALS (url, username, password, userid) VALUES(#{url}, #{username}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insert(Credential credencial);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}  ")
    int delete(Credential credencial);

    @Update("UPDATE CREDENTIALS SET url = #{url}  , username = #{username}, password = #{password}  WHERE noteid = #{credentialid} ")
    int update(Credential credencial);

}
