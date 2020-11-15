package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String postFile(Authentication authentication, @RequestParam("fileUpload") MultipartFile file, Model model) {

        User user = userService.getUser(authentication.getName());
        try {
            File newFile = new File(null, file.getOriginalFilename(),file.getContentType(),Long.toString(file.getSize()), user.getUserid(),file.getBytes());

            File oldFile = fileService.getFilebyNameAndUser(newFile.getFilename(),newFile.getUserid());

            if (oldFile != null){
                model.addAttribute("error", true);
                model.addAttribute("message", "Sorry, two files with the same name is not allowed!");

            }else{
                model.addAttribute("add", true);
                if (newFile.getFileId() == null){
                    this.fileService.addFile(newFile);
                    model.addAttribute("message", "File Added successfully");

                }else {
                    this.fileService.updateFile(newFile);
                    model.addAttribute("message", "File Updated successfully");

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("files", this.fileService.getFiles(user));
        return "result";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model) {
        try {
            this.fileService.deleteFile(fileId);
            model.addAttribute("delete", true);
            model.addAttribute("message", "File Deleted successfully");
        }catch (Exception e ){
            model.addAttribute("error", true);
            model.addAttribute("message", "Something went wrong with File");

        }
        return "result";

    }

    //This look likes the best way to see the files
    @GetMapping("/get/{fileId}")
    public ResponseEntity<Resource> viewFile(@PathVariable Integer fileId){

        File file = fileService.getFile(fileId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getContenttype())).
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getFilename() + "\"")
                .body(new ByteArrayResource(file.getFiledata()));


    }

}
