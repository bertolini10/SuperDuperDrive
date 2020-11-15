package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;
    @Autowired
    private UserMapper userMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int addFile(File file) {
        File newFile = new File(0,file.getFilename(),file.getContenttype(),file.getFilesize(),file.getUserid(),file.getFiledata());
        return fileMapper.insert(newFile);
    }

    public int deleteFile(Integer fileId) {
        return fileMapper.delete(fileMapper.getFile(fileId));
    }

    public int updateFile(File file) {
        return fileMapper.update(file);
    }

    public File getFile(Integer fileid) {
        return fileMapper.getFile(fileid);
    }

    public List<File> getFiles(User user) {
        return fileMapper.getAllFilesbyUser(user.getUserid());
    }
}
