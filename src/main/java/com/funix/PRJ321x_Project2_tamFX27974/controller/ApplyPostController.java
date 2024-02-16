package com.funix.PRJ321x_Project2_tamFX27974.controller;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Cv;
import com.funix.PRJ321x_Project2_tamFX27974.service.ApplyPostService;
import com.funix.PRJ321x_Project2_tamFX27974.service.CvService;
import com.funix.PRJ321x_Project2_tamFX27974.service.UserService;
import com.funix.PRJ321x_Project2_tamFX27974.utils.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.funix.PRJ321x_Project2_tamFX27974.controller.CvController.DIR_CV;
import static com.funix.PRJ321x_Project2_tamFX27974.controller.CvController.UPLOAD_DIR;


@RestController
public class ApplyPostController {

    final
    UserService userService;
    final
    CvService cvService;
    private final ApplyPostService applyPostService;

    public ApplyPostController(ApplyPostService applyPostService, CvService cvService, UserService userService) {
        this.applyPostService = applyPostService;
        this.cvService = cvService;
        this.userService = userService;
    }

    @PostMapping("/api/apply-job")
    public ResponseEntity<?> applyJobNewCv(@RequestParam("idRe") String id, @RequestParam("note") String note, @RequestParam("file") MultipartFile file) {
        System.out.println("applyJob");
        if (file.isEmpty()) {
            // Handle empty file
            return ResponseEntity.ok("false");
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            fileName = fileName.replace(" ", "_");
            Path path = Paths.get(UPLOAD_DIR + DIR_CV + fileName);

            // Create the directory if it doesn't exist
            Files.createDirectories(path.getParent());

            // Get the file and save it to the server
            byte[] bytes = file.getBytes();
            Files.write(path, bytes);

            Cv cv = new Cv(fileName, null);
            cvService.save(cv);

            boolean isAppliedSuccessfully = applyPostService.applyWidthNewCv(id, note, fileName);
            if (isAppliedSuccessfully) {
                return ResponseEntity.ok("true");
            } else {
                return ResponseEntity.ok("false");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        if (!Utils.isLogged()) {
            return ResponseEntity.ok("false");
        } else {
            return ResponseEntity.ok("true");
        }
    }

    @PostMapping("/api/apply-job1")
    public ResponseEntity<?> applyJob(@RequestParam("idRe") String id, @RequestParam("note") String note) {
        System.out.println("applyJob1");
        System.out.println("idRe: " + id);
        System.out.println("note: " + note);
        boolean userHaveCv = cvService.existsByUserId(userService.getCurrentUser().getId());
        if (!userHaveCv) {
            return ResponseEntity.ok("null-cv");
        }
        boolean isAppliedSuccessfully = applyPostService.apply(id, note);
        if (isAppliedSuccessfully) {
            return ResponseEntity.ok("true");
        } else {
            return ResponseEntity.ok("false");
        }
    }

    @PostMapping("/upload/cv")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            // Handle empty file
            return ResponseEntity.ok("false");
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            fileName = fileName.replace(" ", "_");
            Path path = Paths.get(UPLOAD_DIR + DIR_CV + fileName);

            // Create the directory if it doesn't exist
            Files.createDirectories(path.getParent());

            // Get the file and save it to the server
            byte[] bytes = file.getBytes();
            Files.write(path, bytes);

            Cv cv = new Cv(fileName, userService.getCurrentUser());
            cvService.save(cv);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(
                fileName
        );
    }


}
