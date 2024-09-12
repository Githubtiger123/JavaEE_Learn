package com.learn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Alias("lbwnb")
public class employee {

    public employee(Integer employeeId) {
        System.out.println("构造方法1");
    }

    public employee(Integer employeeId, String lastName, String email) {
        System.out.println("构造方法2");
    }


    int employeeId;
    String lastName;
    String email;
    int departmentId;
    double salary;
    department department;

}
