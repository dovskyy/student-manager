package pl.dovskyy.studentmanager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.dovskyy.studentmanager.model.Student;
import pl.dovskyy.studentmanager.service.StudentService;


import java.util.List;

@Controller
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ModelAndView getStudents(){
        ModelAndView mav = new ModelAndView("list-students"); //list-students is the thymeleaf template name
        List<Student> studentList = studentService.getStudents();
        mav.addObject("students", studentList);
        return mav;
    }

    @PostMapping("/add")
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }


    // example path for DELETE would be:
    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/update/{studentId}")
    public void updateStudent(@PathVariable Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {

        studentService.updateStudent(studentId, name, email);
    }
}
