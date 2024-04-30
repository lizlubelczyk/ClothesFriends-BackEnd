package com.ClothesFriends.ClothesFriendsBackEnd.controller;

import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem;
import com.ClothesFriends.ClothesFriendsBackEnd.service.ClothingItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import java.util.Date;
import java.util.List;

@Controller
public class ClothingItemController {

    @Autowired
    private ClothingItemService clothingItemService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value={"/", "/clothingItems"})
    public String addClothingItem() {
        return "index";
    }

    @PostMapping("/image/saveImageDetails")
    public @ResponseBody ResponseEntity<?> createClothingItem(@RequestParam("name") String name, @RequestParam("description") String description, Model model, final @RequestParam("image") MultipartFile file){
        try{
            String fileName = file.getOriginalFilename();
            if(fileName == null || fileName.contains("..")){
                model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence " + fileName);
                return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
            }
            String[] names = name.split(",");
            String[] descriptions = description.split(",");
            Date createDate = new Date();
            log.info("Name: " + names[0]);
            log.info("Description: " + descriptions[0]);

            byte[] imageData = file.getBytes();
            ClothingItem clothingItem = new ClothingItem();
            clothingItem.setName(names[0]);
            clothingItem.setDescription(descriptions[0]);
            clothingItem.setImage(imageData);
            clothingItem.setCreateDate(createDate);
            clothingItemService.saveClothingItem(clothingItem);
            log.info("HttpStatus: " + new ResponseEntity<>(HttpStatus.OK));
            return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            log.info("Exception: " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/image/show")
    public String show(Model map){
        List<ClothingItem> clothingItems = clothingItemService.getAllClothingItems();
        map.addAttribute("clothingItems", clothingItems);
        return "clothingItems";
    }
}
