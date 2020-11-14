package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    Credential getCredential(Integer credentialid);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credential> getCredentialsByUser(Integer userid);

    @Insert("INSERT INTO CREDENTIALS (url, username, password, userid) VALUES(#{url}, #{username}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insert(Credential credencial);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}  ")
    int delete(Credential credencial);

    @Update("UPDATE CREDENTIALS SET url = #{url}  , username = #{username}, password = #{password}  WHERE credentialid = #{credentialid} ")
    int update(Credential credencial);

}
