package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;


    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private  UserMapper userMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public int addCredential(Credential credential) {
        SecureRandom random = new SecureRandom();
        String generatedSecretKey = encryptionService.getSecureKey();
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), generatedSecretKey);
        Credential newCredential = new Credential(0,credential.getUrl(),credential.getUsername(), encryptedPassword,generatedSecretKey,credential.getUserid(),credential.getPassword());
        return credentialMapper.insert(newCredential);
    }

    public int deleteCredential(Integer noteId) {
        return credentialMapper.delete(credentialMapper.getCredential(noteId));
    }

    public int updateCredential(Credential credential) {
        String password=credential.getPassword();
        String generatedSecretKey = encryptionService.getSecureKey();
        String encryptedPassword = encryptionService.encryptValue(password, generatedSecretKey);
        credential.setPassword(encryptedPassword);
        credential.setKey(generatedSecretKey);
        return credentialMapper.update(credential);
    }

    public List<Credential> getCredentials(User user) {
        List<Credential>  list = credentialMapper.getCredentialsByUser(user.getUserid());
        for(Credential credential: list)
        {
            String decryptedPassword=encryptionService.decryptValue(credential.getPassword(),credential.getKey());
            credential.setDpassword(decryptedPassword);
        }
        return list;
    }

}



