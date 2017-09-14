package com.github.elizabetht;

import com.github.elizabetht.model.Student;
import com.github.elizabetht.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void save(Student student) {
       studentRepository.save(student);
    }

    public boolean findByUserName(String userName)  {
        String user = studentRepository.findByLogin(userName);
        if(!StringUtils.isEmpty(user)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean findByLogin(User user) {
        String userPwd = studentRepository.findByLogin(user.getUserName());
        if(!StringUtils.isEmpty(userPwd) && userPwd.equals(user.getPassword())){
            return true;
        } else {
            return false;
        }
    }
}
