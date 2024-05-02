package com.af.oshaeat.Service;

import com.af.oshaeat.entity.AttendanceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    @Aut    owired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<AttendanceDTO> getAttendanceByClass(Long classId) {
        // Logic to retrieve attendance information for the given classId
        List<AttendanceEntity> attendanceList = attendanceRepository.findByClassId(classId);
        return AttendanceMapper.toDTOList(attendanceList);
    }

    public boolean markAttendance(AttendanceDTO attendanceDTO) {
        // Logic to mark attendance for the given studentId and classId
        try {
            Attendance attendance = attendanceRepository.findByClassIdAndStudentId(attendanceDTO.getClassId(), attendanceDTO.getStudentId());
            if (attendance != null) {
                attendance.setPresent(attendanceDTO.isPresent());
            } else {
                attendance = AttendanceMapper.toEntity(attendanceDTO);
            }
            attendanceRepository.save(attendance);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clearAttendance(Long classId) {
        // Logic to clear all attendance records for the given classId
        try {
            attendanceRepository.deleteByClassId(classId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean confirmAttendance(Long classId) {
        // Logic to confirm all attendance records for the given classId
        try {
            List<Attendance> attendanceList = attendanceRepository.findByClassId(classId);
            for (Attendance attendance : attendanceList) {
                attendance.setPresent(true);
            }
            attendanceRepository.saveAll(attendanceList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
