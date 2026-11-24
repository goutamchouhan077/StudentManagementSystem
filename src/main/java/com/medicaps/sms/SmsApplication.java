package com.medicaps.sms;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.medicaps.sms.model.*;
import com.medicaps.sms.repository.*;
import java.time.LocalDate;
import java.math.BigDecimal;
@SpringBootApplication
public class SmsApplication {
 public static void main(String[] args){SpringApplication.run(SmsApplication.class,args);}
 @Bean BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean CommandLineRunner seed(UserRepository users, StudentRepository students, TeacherRepository teachers, CourseRepository courses, SubjectRepository subjects, AttendanceRepository attendance, MarkRepository marks, FeeRepository fees, BCryptPasswordEncoder encoder){return args->{
  if(users.count()==0){
   User admin=new User();admin.setUsername("admin");admin.setName("System Administrator");admin.setEmail("admin@medicaps.ac.in");admin.setPassword(encoder.encode("Admin@123"));admin.setRole("ADMIN");users.save(admin);
   User teacherUser=new User();teacherUser.setUsername("teacher");teacherUser.setName("Demo Teacher");teacherUser.setEmail("teacher@medicaps.ac.in");teacherUser.setPassword(encoder.encode("Teacher@123"));teacherUser.setRole("TEACHER");users.save(teacherUser);
   User studentUser=new User();studentUser.setUsername("STU001");studentUser.setName("Aarav Sharma");studentUser.setEmail("aarav@example.com");studentUser.setPassword(encoder.encode("Student@123"));studentUser.setRole("STUDENT");users.save(studentUser);
  }
  if(students.count()==0){Student st=new Student();st.setStudentId("STU001");st.setFullName("Aarav Sharma");st.setEmail("aarav@example.com");st.setPhone("9876543210");st.setDepartment("Computer Science");st.setCourse("B.Tech CSE");st.setSemester(5);st.setAdmissionDate(LocalDate.of(2024,7,15));students.save(st);}
  if(teachers.count()==0){Teacher t=new Teacher();t.setEmployeeId("TCH001");t.setFullName("Demo Teacher");t.setEmail("teacher@medicaps.ac.in");t.setDepartment("Computer Science");teachers.save(t);}
  if(courses.count()==0){Course c=new Course();c.setName("B.Tech CSE");c.setDepartment("Computer Science");c.setDurationSemesters(8);courses.save(c);}
  if(subjects.count()==0){Subject sub=new Subject();sub.setCode("CS501");sub.setName("Computer Networks");sub.setCourse("B.Tech CSE");sub.setSemester(5);sub.setMaximumMarks(100.0);subjects.save(sub);}
  if(attendance.count()==0){Attendance a=new Attendance();a.setStudentId("STU001");a.setAttendanceDate(LocalDate.now());a.setCourse("B.Tech CSE");a.setStatus("PRESENT");a.setMarkedBy("teacher");attendance.save(a);}
  if(marks.count()==0){Mark m=new Mark();m.setStudentId("STU001");m.setSubject("Computer Networks");m.setCourse("B.Tech CSE");m.setSemester(5);m.setObtainedMarks(86.0);m.setMaximumMarks(100.0);m.setGrade("A");marks.save(m);}
  if(fees.count()==0){Fee f=new Fee();f.setStudentId("STU001");f.setTotalFees(new BigDecimal("120000"));f.setPaidFees(new BigDecimal("90000"));f.setPendingFees(new BigDecimal("30000"));f.setUpdatedAt(LocalDate.now());fees.save(f);}
 };}
}
