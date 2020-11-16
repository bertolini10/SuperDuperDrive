package com.udacity.jwdnd.course1.cloudstorage.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/error")
public class ErrorController {
			   
	    @GetMapping()
	    public String handleError(HttpServletRequest request) {
	        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	        if (status != null) {
	            Integer statusCode = Integer.valueOf(status.toString());	            
	            if (statusCode == HttpStatus.NOT_FOUND.value()) {
	                return "error/404";
	            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	                return "error/500";
	            }
	        }
	        return "error";
	    }

	    public String getErrorPath() {
	        return null;
	    }
}

