package apim.github.tutorial;

import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestCodeO2O {

	private static final Properties prop;

	static {
		prop = new Properties();
		prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/testdb");
		prop.setProperty("hibernate.connection.username", "root");
		prop.setProperty("hibernate.connection.password", "root");
		prop.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
	}

	public static void main(String... args) {
		SessionFactory factory = new Configuration()
				.addPackage("apim.github.tutorial")
				.addProperties(prop)
				.addAnnotatedClass(EmployeeO2O.class)
				.addAnnotatedClass(Laptop.class)
				.buildSessionFactory();

		createRecord(factory);
		readRecord(factory);

		factory.close();
	}

	private static void createRecord(SessionFactory factory) {
		Session session = null;
		Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Laptop lp1 = new Laptop("Sony");
			Laptop lp2 = new Laptop("Dell");
			EmployeeO2O emp1 = new EmployeeO2O(1, "Rashid", "Developer", 100, lp1);
			EmployeeO2O emp2 = new EmployeeO2O(2, "Abdul", "Senior Developer", 140, lp2);
			lp1.setEmployee(emp1);
			lp2.setEmployee(emp2);
			session.save(emp1);
			session.save(emp2);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println("Exception occurred: " + ex.getMessage());
		} finally {
			session.close();
		}
	}

	private static void readRecord(SessionFactory factory) {
		Session session = null;
		Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			List result = session.createQuery("from EmployeeO2O").list();
			for (Object obj : result) {
				EmployeeO2O e = (EmployeeO2O) obj;
				System.out.print("Name: " + e.getName() + ", Designation: " + e.getDesignation() + ", Salary: " + e.getSalary());
				System.out.println(", Laptop: " + e.getLaptop().getBrand());
			}
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println("Exception occurred: " + ex.getMessage());
		} finally {
			session.close();
		}
	}

}
