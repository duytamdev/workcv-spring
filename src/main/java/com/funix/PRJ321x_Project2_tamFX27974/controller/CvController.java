package com.funix.PRJ321x_Project2_tamFX27974.controller;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Company;
import com.funix.PRJ321x_Project2_tamFX27974.service.CompanyService;
import com.funix.PRJ321x_Project2_tamFX27974.service.CvService;
import com.funix.PRJ321x_Project2_tamFX27974.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class CvController {

    public static final String UPLOAD_DIR = "src/main/resources/static/assets/";
    public static final String DIR_CV = "user-cvs/";
    private static final String DIR_USER = "user-images/";
    private static final String DIR_COMPANY = "company-images/";
    private final UserService userService;
    private final CompanyService companyService;
    private final CvService cvService;

    public CvController(UserService userService, CompanyService companyService, CvService cvService) {
        this.userService = userService;
        this.companyService = companyService;
        this.cvService = cvService;
    }

    @PostMapping("user/cv/delete")
    public String deleteCv(@RequestParam("id") String id) {
        cvService.deleteById(Integer.parseInt(id));
        return "redirect:/profile";
    }

    @PostMapping("/user/upload/avt")
    public ResponseEntity<String> handleFileUploadAvt(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            // Handle empty file
            return ResponseEntity.ok(null);
        }
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            fileName = fileName.replace(" ", "_");
            Path path = Paths.get(UPLOAD_DIR + DIR_USER + fileName);

            // Create the directory if it doesn't exist
            Files.createDirectories(path.getParent());

            // Get the file and save it to the server
            byte[] bytes = file.getBytes();
            Files.write(path, bytes);

            return ResponseEntity.ok(userService.updateAvatarById(userService.getCurrentUser().getId(), fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/user/upload-company")
    public String uploadLogoCompany(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            // Handle empty file
            return "redirect:/";
        }
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            fileName = fileName.replace(" ", "_");
            Path path = Paths.get(UPLOAD_DIR + DIR_COMPANY + fileName);

            // Create the directory if it doesn't exist
            Files.createDirectories(path.getParent());

            // Get the file and save it to the server
            byte[] bytes = file.getBytes();
            Files.write(path, bytes);

            System.out.println("File uploaded successfully! Path: " + path);
            if (userService.getCurrentUser().getCompany() == null) {
                Company company = new Company();
                company.setLogo(fileName);
                companyService.save(company);
            } else {
                companyService.uploadLogo(userService.getCurrentUser().getCompany().getId(), fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
