package com.af.oshaeat.Service;

import com.af.oshaeat.dto.StudentDTO;
import com.af.oshaeat.entity.AssignmentEntity;
import com.af.oshaeat.entity.StudentEntity;
import com.af.oshaeat.responsory.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class TeacherService {
    private static final String UPLOAD_DIR = "./uploads/";

    @Autowired
    private AssignmentRepository assignmentRepository;

    public boolean addStudent(StudentDTO studentDTO) {
        // Logic to add student
    }

    public void uploadAssignment(Long subjectId, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        // Get original filename
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Save file to the upload directory
        Path uploadPath = Paths.get(UPLOAD_DIR + fileName);
        Files.copy(file.getInputStream(), uploadPath);

        // Save assignment information to the database
        AssignmentEntity assignment = new AssignmentEntity();
        assignment.setId(subjectId);
        assignment.setFileName(fileName);
        assignment.setUploadDate(new Date());
        // You can set other assignment details here if needed
        assignmentRepository.save(assignment);
    }
}
