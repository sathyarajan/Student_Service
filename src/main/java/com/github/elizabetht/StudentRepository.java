package com.github.elizabetht;

import com.github.elizabetht.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Repository
public class StudentRepository {

    private static final Logger LOG = LoggerFactory.getLogger(StudentRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String SQL_INSERT = "insert into student(userName, password, firstName, lastName, dateOfBirth, emailAddress) values (?, ?, ?, ?, ?, ?)";

    private final String SQL_FIND_LOGIN = "select password from student where userName = ?";

    public void save(Student student) throws DataAccessException {
        try {
            jdbcTemplate.update(SQL_INSERT, student.getUserName(), student.getPassword(), student.getFirstName(),
                    student.getLastName(),
                    new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy")
                            .parse(student.getDateOfBirth().substring(0, 10)).getTime()), student.getEmailAddress());
        } catch (Exception ex) {
            LOG.error("Exception while update in save method ", ex);
            throw new DataAccessException("Exception while update in save method ", ex);
        }
    }

    public String findByLogin(String userName)throws DataAccessException {
        String userPwd = null;
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(SQL_FIND_LOGIN, userName);
            while (rowSet.next()) {
                userPwd = rowSet.getString(1);
            }
        } catch (Exception ex) {
            LOG.error("Exception while executing query in findByLogin method ", ex);
            throw new DataAccessException("Exception while executing query in findByLogin method ", ex);
        }
        return userPwd;
    }
}
