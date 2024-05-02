package com.af.oshaeat.responsory;

import com.af.oshaeat.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<StudentEntity, Long> {
}
