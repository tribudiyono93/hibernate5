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
 
public class SaveOrUpdateTransientDemo {
 
   private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
 
   private static TimeKeeper saveOrUpdate_Transient(Session session,
           Employee emp) {
 
       // See configuration of timekeeperId:
       // @GeneratedValue(generator = "uuid")
       // @GenericGenerator(name = "uuid", strategy = "uuid2")
       // Create an Object, TimeKeeper state.
	   TimeKeeper tk3 = new TimeKeeper();
 
       tk3.setEmployee(emp);
       tk3.setInOut(TimeKeeper.IN);
       tk3.setDateTime(new Date());
 
       // Now 'tk3' are state Transient.
       System.out.println("- tk3 Persistent? " + session.contains(tk3));
       System.out.println("- tk3.getTimekeeperId() = " + tk3.getTimeKeeperId());
       System.out.println("====== CALL saveOrUpdate(tk).... ===========");
 
       // Here Hibernate checks, 'tk3' have ID or not (timekeeperId)
       // If no, it will be assigned automatically
       session.saveOrUpdate(tk3);
 
       System.out.println("- tk3.getTimekeeperId() = " + tk3.getTimeKeeperId());
 
       // Now 'tk3' has Persistent state
       // It has been managed in Session.
       // But no action insert, or update to DB.
       // ==> true
       System.out.println("- tk3 Persistent? " + session.contains(tk3));
 
       System.out.println("- Call flush..");
 
       // To push data into the DB, call flush().
       // If not call flush() data will be pushed to the DB when calling commit().
       // Now possible to Insert or Update DB. (!!!)
       // Depending on the ID of 'tk3' exists in the DB or not
       session.flush();
 
       String timekeeperId = tk3.getTimeKeeperId();
       System.out.println("- timekeeperId = " + timekeeperId);
       System.out.println("- inOut = " + tk3.getInOut());
       System.out.println("- dateTime = " + df.format(tk3.getDateTime()));
       System.out.println();
       return tk3;
   }
 
   public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();
 
       Session session = factory.getCurrentSession();
       Employee emp = null;
       try {
           session.getTransaction().begin();
 
           emp = DataUtils.findEmployee(session, "E7499");
 
           saveOrUpdate_Transient(session, emp);
 
           session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
       }
   }
}