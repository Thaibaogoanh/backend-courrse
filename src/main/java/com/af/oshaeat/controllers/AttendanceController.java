package com.af.oshaeat.controllers;

import com.af.oshaeat.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teacher")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @GetMapping("/attendance")
    public ResponseEntity<List<AttendanceDTO>> getAttendance(@RequestParam("classId") Long classId) {
        List<AttendanceDTO> attendanceList = attendanceService.getAttendanceByClass(classId);
        return ResponseEntity.ok(attendanceList);
    }

    @PostMapping("/attendance/mark")
    public ResponseEntity<String> markAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        boolean success = attendanceService.markAttendance(attendanceDTO);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Attendance marked successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to mark attendance");
        }
    }

    @DeleteMapping("/attendance/clear")
    public ResponseEntity<String> clearAttendance(@RequestParam("classId") Long classId) {
        boolean success = attendanceService.clearAttendance(classId);
        if (success) {
            return ResponseEntity.ok("Attendance cleared successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to clear attendance");
        }
    }

    @PostMapping("/attendance/confirm")
    public ResponseEntity<String> confirmAttendance(@RequestParam("classId") Long classId) {
        boolean success = attendanceService.confirmAttendance(classId);
        if (success) {
            return ResponseEntity.ok("Attendance confirmed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to confirm attendance");
        }
    }
}
