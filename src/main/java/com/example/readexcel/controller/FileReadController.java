package com.example.readexcel.controller;

import com.example.readexcel.services.ReadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileReadController {

    @Autowired
    private ReadFileService readFile ;

    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            readFile.readFileAndStoreInDatabase(file);
            return "Done";
        }

        return  "Not Done";
    }
}
