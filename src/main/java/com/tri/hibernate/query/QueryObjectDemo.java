package com.tri.hibernate.query;

import java.util.List;

import org.hibernate.query.Query;

import com.tri.hibernate.HibernateUtil2;
import com.tri.hibernate.HibernateUtils;
import com.tri.hibernate.entities.Employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
 
public class QueryObjectDemo {
 
   public static void main(String[] args) {
       //SessionFactory factory = HibernateUtils.getSessionFactory();
	   SessionFactory factory = HibernateUtil2.createSessionFactory("development");
       Session session = factory.getCurrentSession();
 
       try {
            
           // All the action with DB via Hibernate
           // must be located in one transaction.
           // Start Transaction.            
           session.getTransaction().begin();
 
    
            
           // Create an HQL statement, query the object.
           // Equivalent to the SQL statement:
           // Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
           String sql = "Select e from " + Employee.class.getName() + " e "
                   + " order by e.empName, e.empNo ";
 
   
           // Create Query object.
           Query<Employee> query = session.createQuery(sql);
 
    
           // Execute query.
           List<Employee> employees = query.getResultList();
 
           for (Employee emp : employees) {
               System.out.println("Emp: " + emp.getEmpNo() + " : "
                       + emp.getEmpName());
           }
  
           // Commit data.
           session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           // Rollback in case of an error occurred.
           session.getTransaction().rollback();
       }
       System.out.println(factory.toString());
       
     //SessionFactory factory = HibernateUtils.getSessionFactory();
	   SessionFactory factory2 = HibernateUtil2.createSessionFactory("development");
       Session session2 = factory2.getCurrentSession();
 
       try {
            
           // All the action with DB via Hibernate
           // must be located in one transaction.
           // Start Transaction.            
           session2.getTransaction().begin();
 
    
            
           // Create an HQL statement, query the object.
           // Equivalent to the SQL statement:
           // Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
           String sql = "Select e from " + Employee.class.getName() + " e "
                   + " order by e.empName, e.empNo ";
 
   
           // Create Query object.
           Query<Employee> query = session2.createQuery(sql);
 
    
           // Execute query.
           List<Employee> employees = query.getResultList();
 
           for (Employee emp : employees) {
               System.out.println("Emp: " + emp.getEmpNo() + " : "
                       + emp.getEmpName());
           }
  
           // Commit data.
           session2.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           // Rollback in case of an error occurred.
           session2.getTransaction().rollback();
       }
       System.out.println(factory2.toString());
   }
    
}
