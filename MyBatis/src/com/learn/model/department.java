package com.learn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class department {

    int department_id;
    String department_name;
    int manager_id;
    List<employee> employees;
}
