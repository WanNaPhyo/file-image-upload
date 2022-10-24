package com.example.revesion8.controller;


import com.example.revesion8.util.InMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class Example1 {

    @Autowired
    private InMemoryStore inMemoryStorage;

    @GetMapping("/example1/gallary")
    public String example16(Model model){
        model.addAttribute("imagesNames",inMemoryStorage.getNames());
        return "gallary";
    }

    @ResponseBody
    @GetMapping(value = "/example1/gallary/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] showImage(@PathVariable String imageName){
        return inMemoryStorage.getByName(imageName);
    }

    @PostMapping("/example1/gallary")
    public String fileUpload(@RequestPart("file")MultipartFile file)throws IOException {
        inMemoryStorage.store(file.getOriginalFilename(),file.getBytes());
        return "redirect:/example1/gallary";
    }
}
