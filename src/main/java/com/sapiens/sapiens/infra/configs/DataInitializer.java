package com.sapiens.sapiens.infra.configs;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.sapiens.sapiens.domain.admin.Admin;
import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.domain.school.School;
import com.sapiens.sapiens.domain.schoolClass.SchoolClass;
import com.sapiens.sapiens.domain.secretariat.Secretariat;
import com.sapiens.sapiens.domain.student.Student;
import com.sapiens.sapiens.domain.superadmin.SuperAdmin;
import com.sapiens.sapiens.domain.teacher.Teacher;
import com.sapiens.sapiens.domain.user.UserRole;
import com.sapiens.sapiens.repositories.AdminRepository;
import com.sapiens.sapiens.repositories.AuthRepository;
import com.sapiens.sapiens.repositories.DisciplineRepository;
import com.sapiens.sapiens.repositories.SchoolClassRepository;
import com.sapiens.sapiens.repositories.SchoolRepository;
import com.sapiens.sapiens.repositories.SecretariatRepository;
import com.sapiens.sapiens.repositories.StudentRepository;
import com.sapiens.sapiens.repositories.TeacherRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@Transactional
public class DataInitializer implements CommandLineRunner {

    private final BCryptPasswordEncoder encoder;
    private final AuthRepository authRepository;
    private final AdminRepository adminRepository;
    private final SecretariatRepository secretariatRepository;
    private final SchoolRepository schoolRepository;
    private final DisciplineRepository disciplineRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public void run(String... args) {
        System.out.println("######## Initializing Data ########");
        initializeData();
        System.out.println("######## Data Initialized ########");
    }

    public void initializeData() {
        if (authRepository.findByRole(UserRole.SUPERADMIN).isEmpty()) {
            SuperAdmin superAdmin = createSuperAdmin();
            Secretariat secretariat = createSecretariat(superAdmin);
            School school = createSchool(secretariat);
            SchoolClass schoolClass = createSchoolClass(school);
            List<Teacher> teachers = createTeachers(school);
            createDisciplines(school, schoolClass, teachers);
            createStudents(school, schoolClass);
        }
    }

    private SuperAdmin createSuperAdmin() {
        var user = new SuperAdmin(
                "Sapiens Secretaria",
                "super@mail.com",
                encoder.encode("super123"),
                UserRole.SUPERADMIN);
        user.setFirstLogin(false);
        return authRepository.save(user);
    }

    private Secretariat createSecretariat(SuperAdmin superAdmin) {
        var secretariat = new Secretariat();
        secretariat.setName("Sapiens Educação");
        secretariat.setSuperAdmin(superAdmin);
        secretariat.setAddress("Rua A");
        secretariat.setCity("Cidade A");
        secretariat.setState("Estado A");
        secretariat.setZipCode("00000-000");
        secretariat.setEmail("school@mail.com");
        secretariat.setPhone("0000-0000");
        return secretariatRepository.save(secretariat);
    }

    private School createSchool(Secretariat secretariat) {
        var admin = createAdmin();
        var school = new School();
        school.setAddress("Rua A");
        school.setCity("Cidade A");
        school.setState("Estado A");
        school.setZipCode("00000-000");
        school.setEmail("school@mail.com");
        school.setPhone("0000-0000");
        school.setName("Sapiens Escola");
        school.setSecretariat(secretariat);
        school.setAdmin(admin);
        return schoolRepository.save(school);
    }

    private Admin createAdmin() {
        var admin = new Admin();
        admin.setName("Admin da Escola");
        admin.setEmail("admin@mail.com");
        admin.setPassword(encoder.encode("admin123"));
        admin.setRole(UserRole.ADMIN);
        return adminRepository.save(admin);
    }

    private SchoolClass createSchoolClass(School school) {
        var schoolClass = new SchoolClass();
        schoolClass.setCode("1A");
        schoolClass.setSchool(school);
        return schoolClassRepository.save(schoolClass);
    }

    private List<Teacher> createTeachers(School school) {
        var teacher0 = createTeacher("Prof. Leandro", "leandro@mail.com", "leandro123", school);
        var teacher1 = createTeacher("Prof. Maria", "maria@mail.com", "maria123", school);
        return teacherRepository.saveAll(List.of(teacher0, teacher1));
    }

    private Teacher createTeacher(String name, String email, String password, School school) {
        var teacher = new Teacher();
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setPassword(encoder.encode(password));
        teacher.setRole(UserRole.TEACHER);
        teacher.setSchool(school);
        teacher.setCode("2024ABC0" + (teacherRepository.count() + 1));
        return teacher;
    }

    private void createDisciplines(School school, SchoolClass schoolClass, List<Teacher> teachers) {
        final double LESSON_TIME = 50.0 / 60.0;

        var discipline0 = new Discipline();
        discipline0.setCode("MAT");
        discipline0.setName("Matemática");
        discipline0.setManyLessons(60);
        discipline0.setManyHours(discipline0.getManyLessons() * LESSON_TIME);
        discipline0.setSchool(school);
        discipline0.setSchoolClass(schoolClass);
        discipline0.setTeacher(teachers.get(0));

        var discipline1 = new Discipline();
        discipline1.setCode("FIS");
        discipline1.setName("Física");
        discipline1.setManyLessons(45);
        discipline1.setManyHours(discipline1.getManyLessons() * LESSON_TIME);
        discipline1.setSchool(school);
        discipline1.setSchoolClass(schoolClass);
        discipline1.setTeacher(teachers.get(0));

        var discipline2 = new Discipline();
        discipline2.setCode("BIO");
        discipline2.setName("Biologia");
        discipline2.setManyLessons(50);
        discipline2.setManyHours(discipline2.getManyLessons() * LESSON_TIME);
        discipline2.setSchool(school);
        discipline2.setSchoolClass(schoolClass);
        discipline2.setTeacher(teachers.get(1));

        var discipline3 = new Discipline();
        discipline3.setCode("HIS");
        discipline3.setName("História");
        discipline3.setManyLessons(40);
        discipline3.setManyHours(discipline3.getManyLessons() * LESSON_TIME);
        discipline3.setSchool(school);
        discipline3.setSchoolClass(schoolClass);
        discipline3.setTeacher(teachers.get(1));

        disciplineRepository.saveAll(List.of(discipline0, discipline1, discipline2, discipline3));
    }

    private void createStudents(School school, SchoolClass schoolClass) {
        var student0 = new Student();
        student0.setName("Jonas");
        student0.setEmail("jonas@mail.com");
        student0.setPassword(encoder.encode("jonas123"));
        student0.setRole(UserRole.STUDENT);
        student0.setSchool(school);
        student0.setSchoolClass(schoolClass);
        student0.setMatriculation("2024A001");

        var student1 = new Student();
        student1.setName("Kaio");
        student1.setEmail("kaio@mail.com");
        student1.setPassword(encoder.encode("kaio123"));
        student1.setRole(UserRole.STUDENT);
        student1.setSchool(school);
        student1.setSchoolClass(schoolClass);
        student1.setMatriculation("2024A002");

        var student2 = new Student();
        student2.setName("Lucas");
        student2.setEmail("lucas@mail.com");
        student2.setPassword(encoder.encode("lucas123"));
        student2.setRole(UserRole.STUDENT);
        student2.setSchool(school);
        student2.setSchoolClass(schoolClass);
        student2.setMatriculation("2024A003");

        studentRepository.saveAll(List.of(student0, student1, student2));
    }

}
