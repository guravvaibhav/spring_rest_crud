package spring_rest_crud.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring_rest_crud.model.Employee;
@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	
	@Autowired
	SessionFactory factory;

	@Override
	public Employee createEmployee(Employee employee) {
		Session session=factory.openSession();
		Transaction tx = session.beginTransaction();
		Serializable save = session.save(employee);
		Employee emp=session.get(Employee.class, save);
		tx.commit();
		return emp;
	}

	@Override
	public Employee getEmployee(int id) {
		Session session=factory.openSession();
		Transaction tx = session.beginTransaction();
		Employee emp=session.get(Employee.class, id);
		tx.commit();
		return emp;
	}

	@Override
	public List<Employee> getEmployee() {
		Session session=factory.openSession();
		Transaction tx = session.beginTransaction();
		List<Employee> empList=session.createSQLQuery("select * from Employee").list();
		tx.commit();
		return empList;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Session session=factory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(employee);
		Employee emp=session.get(Employee.class, employee.getId());
		tx.commit();
		return emp;
	}

	@Override
	public int deleteEmp(int id) {
		Session session=factory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.delete(session.get(Employee.class, id));
		tx.commit();
		return id;
	}
	
	

}
