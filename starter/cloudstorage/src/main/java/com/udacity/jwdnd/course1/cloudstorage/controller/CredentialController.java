package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String postCredential(Authentication authentication, @ModelAttribute(value = "credential") Credential credential, Model model) {

        User user = userService.getUser(authentication.getName());
        credential.setUserid(user.getUserid());

        if (credential.getCredentialid() == null){
            this.credentialService.addCredential(credential);
            model.addAttribute("message", "Credential Added successfully");
        }else {
            this.credentialService.updateCredential(credential);
            model.addAttribute("message", "Credential Updated successfully");
        }
        model.addAttribute("credentials", this.credentialService.getCredentials(user));
        model.addAttribute("add", true);
        return "result";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId,Model model) {
        try {
            this.credentialService.deleteCredential(credentialId);
            model.addAttribute("delete", true);
            model.addAttribute("message", "Credential Deleted successfully");
        }catch (Exception e ){
            model.addAttribute("error", true);
            model.addAttribute("message", "Something went wrong Credential");

        }
        return "result";

    }

}
