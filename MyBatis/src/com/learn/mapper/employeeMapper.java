package com.learn.mapper;

import com.learn.model.employee;

import java.util.List;
import java.util.Map;

public interface employeeMapper {

    List<employee> selectStudent();

    List<employee> selectStudentMap();

    employee getEmployeeBySid(int sid);

    int addEmployee(employee employee);
    List<employee> selectEmpAndDept();

    employee getEmployeeDoubleCondition(int eid);
    employee getEmployeeManyCondition(int eid);
}
