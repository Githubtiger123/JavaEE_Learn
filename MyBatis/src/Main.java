import com.learn.mapper.departmentAnnotationMapper;
import com.learn.mapper.departmentMapper;
import com.learn.mapper.employeeAnnotationMapper;
import com.learn.mapper.employeeMapper;
import com.learn.model.department;
import com.learn.model.employee;
import com.learn.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//
//        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
//            employeeMapper mapper = sqlSession.getMapper(employeeMapper.class);
////            List<employee> list = mapper.selectStudentMap();
////            list.forEach(System.out::println);
//            // 条件查询一条记录
////            employee employeeBySid = mapper.getEmployeeBySid(101);
////            System.out.println(employeeBySid);
//
//            //插入一条记录
//            employee e = new employee(9999, "test_name", "test_email", 9990, 789.0, null);
////            int i = mapper.addEmployee(e);
////            System.out.println(i);
//
//
////            departmentMapper deptMapper = sqlSession.getMapper(departmentMapper.class);
//////            department dept = deptMapper.getDepartmentById(50);
//////            System.out.println(dept);
////
////            List<employee> departmentById = mapper.selectEmpAndDept();
////            departmentById.forEach(System.out::println);
//
//            //测试事务
////            sqlSession.rollback();
////            sqlSession.commit();
//
////            employee employeeDoubleCondition = mapper.getEmployeeDoubleCondition(100);
////            System.out.println(employeeDoubleCondition);
////            employee employeeManyCondition = mapper.getEmployeeManyCondition(103);
////            System.out.println(employeeManyCondition);
//
//            //测试缓存
////            employee e1 = mapper.getEmployeeBySid(101);
////            employee e2 = mapper.getEmployeeBySid(101);
////            System.out.println(e1 == e2); //true
//
////            employee e1 = mapper.getEmployeeBySid(101);
////            employee temp = new employee(9999, "test_name", "test_email", 9990, 789.0, null);
////            int i = mapper.addEmployee(temp);
////            employee e2 = mapper.getEmployeeBySid(101);
////            System.out.println(e1 == e2); //false 一级缓存，在进行DML操作后，会使得缓存失效
//        }
//
//        //测试多个绘画之间的缓存
//        //一个会话DML操作只会重置当前会话的缓存，不会重置其他会话的缓存，也就是说，其他会话缓存是不会更新的！
//        //一下代码开启后也不是true因为绘画结束后才会写入缓存
//        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
//            employeeMapper mapper = sqlSession.getMapper(employeeMapper.class);
//            employee e11, e22;
//            e11 = mapper.getEmployeeBySid(100);
//            try (SqlSession sqlSession1 = MybatisUtil.getSession(true)) {
//                employeeMapper mapper1 = sqlSession1.getMapper(employeeMapper.class);
//                e22 = mapper1.getEmployeeBySid(100);
//            }
//            System.out.println(e11 == e22);
//        }
//
//        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
//            employeeMapper mapper = sqlSession.getMapper(employeeMapper.class);
//            employee e11, e22;
//            try (SqlSession sqlSession1 = MybatisUtil.getSession(true)) {
//                employeeMapper mapper1 = sqlSession1.getMapper(employeeMapper.class);
//                e22 = mapper1.getEmployeeBySid(100);
//            }
//            e11 = mapper.getEmployeeBySid(100); //有先兆二级缓存
//            System.out.println(e11 == e22); //true
//        }
//
//        employee e11, e22;
//        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
//            employeeMapper mapper = sqlSession.getMapper(employeeMapper.class);
//            e11 = mapper.getEmployeeBySid(100);
//        }
//        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
//            employeeMapper mapper = sqlSession.getMapper(employeeMapper.class);
//            e22 = mapper.getEmployeeBySid(100);
//            System.out.println(e11 == e22); //true
//        }

        //使用注解进行开发
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            employeeAnnotationMapper empMapper = sqlSession.getMapper(employeeAnnotationMapper.class);
            employee e = new employee(897, "Name_name", "email@email.com", 60, 8888.0, null);
//            int i = empMapper.addEmployee(e);
//            System.out.println(i);

//            List<employee> allEmployee = empMapper.getAllEmployee();
//            allEmployee.forEach(System.out::println);

//            employee employeeById = empMapper.getEmployeeById(100);
//            System.out.println(employeeById);

//            departmentAnnotationMapper depMapper = sqlSession.getMapper(departmentAnnotationMapper.class);
//            department d = depMapper.getDepartmentById(60);
//            System.out.println(d);

//            employee departmentById = empMapper.getEmployeeByAndDept(101);
//            System.out.println(departmentById);

            //混合开发
//            department departmentById2 = empMapper.getDepartmentById2(60);
//            System.out.println(departmentById2);

            //测试指定不同的构造方法
//            employee employeeConstruct = empMapper.getEmployeeConstruct(100, "King");
//            System.out.println(employeeConstruct); //employee(employeeId=0, lastName=null, email=null, departmentId=90, salary=24000.0, department=null)
            //因为构造函数著传入了没赋值

            //传入对象类型数据
            int i = empMapper.addEmployeeConstruct(456,new employee(789,"789","789@123.com",60,8956.2,null));
            System.out.println(i);

        }

    }
}