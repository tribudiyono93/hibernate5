package com.tri.hibernate.query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tri.hibernate.DataUtils;
import com.tri.hibernate.HibernateUtils;
import com.tri.hibernate.entities.Employee;
import com.tri.hibernate.entities.TimeKeeper;
 
public class PersistTransientDemo {
 
   private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
 
   private static TimeKeeper persist_Transient(Session session, Employee emp) {
  
       // Note:
       // Configuring of timekeeperId
       // @GeneratedValue(generator = "uuid")
       // @GenericGenerator(name = "uuid", strategy = "uuid2")            
	   TimeKeeper tk1 = new TimeKeeper();
 
       tk1.setEmployee(emp);
       tk1.setInOut(TimeKeeper.IN);
       tk1.setDateTime(new Date());
   
       // Now, 'tk1' is transient object
       System.out.println("- tk1 Persistent? " + session.contains(tk1));
       System.out.println("- tk1.getTimekeeperId() = " + tk1.getTimeKeeperId());
       System.out.println("====== CALL persist(tk).... ===========");
  
        
       // Hibernate assign value to Id of 'tk1'
       // No action to DB.
       session.persist(tk1);
  
       System.out.println("- tk1.getTimekeeperId() = " + tk1.getTimeKeeperId());
  
        
       // Now 'tk1' is Persistent object.
       // But no action with DB.
       // ==> true
       System.out.println("- tk1 Persistent? " + session.contains(tk1));
 
       System.out.println("- Call flush..");
  
        
       // Flush data to DB.
       // Hibernate execute insert statement.
       session.flush();
 
       String timekeeperId = tk1.getTimeKeeperId();
       System.out.println("- timekeeperId = " + timekeeperId);
       System.out.println("- inOut = " + tk1.getInOut());
       System.out.println("- dateTime = " + df.format(tk1.getDateTime()));
       System.out.println();
       return tk1;
   }
 
   public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();
 
       Session session = factory.getCurrentSession();
       Employee emp = null;
       try {
           session.getTransaction().begin();
 
           emp = DataUtils.findEmployee(session, "E7499");
 
           persist_Transient(session, emp);
 
           session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
       }
   }
    
}