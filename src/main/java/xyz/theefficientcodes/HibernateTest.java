package xyz.theefficientcodes;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import xyz.theefficientcodes.dto.Address;
import xyz.theefficientcodes.dto.UserDetails;

public class HibernateTest {

	public static void main(String[] args) {
		UserDetails user=new UserDetails();
		Address address=new Address();
		
		address.setCity("Berhampur");
		address.setState("Odisha");
		address.setStreet("Saradhabali Housing Complex");
		address.setPincode("760010");
		
		user.setUserId(1);
		user.setUserName("Bibhuti");
		user.setHomeAddress(address);
		user.setOfficeAddress(address);
		user.setJoinedDate(new Date());
		user.setDescription("Hot Guy");
		user.getGfAddresses().add(address);
		
		UserDetails user2=new UserDetails();
		Address address2=new Address();
		
		address2.setCity("Berhampur");
		address2.setState("Odisha");
		address2.setStreet("Saradhabali Housing Complex");
		address2.setPincode("760010");
		
		user2.setUserName("Bonty");
		user2.setHomeAddress(address2);
		user2.setOfficeAddress(address2);
		user2.setJoinedDate(new Date());
		user2.setDescription("Hot Guy");
		user2.getGfAddresses().add(address);
		user2.getGfAddresses().add(address2);
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(user2);
		session.getTransaction().commit();
		session.close();
		
		user=null;
		
		session=sessionFactory.openSession();
		session.beginTransaction();
		user=(UserDetails)session.get(UserDetails.class, 1);
		System.out.println("Username retrieved is "+user.getUserName()+" and description is "+user.getDescription());
		session.close();
		
	}

}
