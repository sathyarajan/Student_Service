package com.github.elizabetht;

import com.github.elizabetht.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String SQL_INSERT = "insert into student(userName, password, firstName, lastName, dateOfBirth, emailAddress) values (?, ?, ?, ?, ?, ?)";

    private final String SQL_FIND_LOGIN = "select password from student where userName = ?";

    public void save(Student student) {
        try {
            jdbcTemplate.update(SQL_INSERT, student.getUserName(), student.getPassword(), student.getFirstName(),
                    student.getLastName(),
                    new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy")
                            .parse(student.getDateOfBirth().substring(0, 10)).getTime()), student.getEmailAddress());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String findByLogin(String userName) {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(SQL_FIND_LOGIN, userName);
        String userPwd = null;
        while(rowSet.next()) {
            userPwd = rowSet.getString(1);
        }
        return userPwd;
    }
}
