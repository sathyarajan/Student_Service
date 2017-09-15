package com.github.elizabetht;

import com.github.elizabetht.model.Error;
import com.github.elizabetht.model.Student;
import com.github.elizabetht.model.StudentResponse;
import com.github.elizabetht.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponse save(Student student) {
        StudentResponse response = new StudentResponse();
        try {
            studentRepository.save(student);
            response.setStatus("Success");
        } catch (DataAccessException ex) {
            response.setStatus("Failure");
            response.setError(getErrorMessage(ex));
        }
        return response;
    }

    private Error getErrorMessage(DataAccessException ex) {
        Error exception = new Error();
        exception.setErrorCode(ex.getMessage());
        exception.setErrorDesc(ex.getCause().getMessage());
        return exception;
    }

    public StudentResponse findByUserName(String userName) {
        StudentResponse response = new StudentResponse();
        try {
            String user = studentRepository.findByLogin(userName);
            response.setStatus("Success");
            if(!StringUtils.isEmpty(user)) {
                response.setStudentResult(true);
            } else {
                response.setStudentResult(false);
            }
        } catch (DataAccessException ex) {
            response.setStatus("Failure");
            response.setError(getErrorMessage(ex));
        }
        return response;
    }

    public StudentResponse findByLogin(User user) {
        StudentResponse response = new StudentResponse();
        try {
            String userPwd = studentRepository.findByLogin(user.getUserName());
            response.setStatus("Success");
            if(!StringUtils.isEmpty(userPwd) && userPwd.equals(user.getPassword())){
                response.setStudentResult(true);
            } else {
                response.setStudentResult(false);
            }
        } catch (DataAccessException ex) {
            response.setStatus("Failure");
            response.setError(getErrorMessage(ex));
        }
        return response;
    }
}
