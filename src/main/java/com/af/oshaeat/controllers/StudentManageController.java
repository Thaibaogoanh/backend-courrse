package com.af.oshaeat.controllers;

import com.af.oshaeat.Service.TeacherService;
import com.af.oshaeat.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/teacher")
public class StudentManageController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody StudentDTO studentDTO) {
        boolean success = teacherService.addStudent(studentDTO);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add student");
        }
    }
    @PostMapping("/uploadAssignment/{subjectId}")
    public ResponseEntity<String> uploadAssignment(@PathVariable Long subjectId,
                                                   @RequestParam("file") MultipartFile file) {
        try {
            teacherService.uploadAssignment(subjectId, file);
            return ResponseEntity.status(HttpStatus.CREATED).body("Assignment uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload assignment");
        }
    }
}
