package apim.github.tutorial;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestCodeBiDi {

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
				.addAnnotatedClass(EmployeeBiDi.class)
				.addAnnotatedClass(Address.class)
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
			EmployeeBiDi emp = new EmployeeBiDi(10, "Peter", "Analyst", 150);
			Address addr1 = new Address(1, "765A", "Birk Road", "Downtown", "Tokyo");
			addr1.setEmployee(emp);
			Address addr2 = new Address(2, "407/1", "Creek Lane", "Upper Area", "Ohio");
			addr2.setEmployee(emp);
			List<Address> addrs = new LinkedList<>();
			addrs.add(addr1);
			addrs.add(addr2);
			emp.setAddresses(addrs);
			session.save(emp);
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
			List result = session.createQuery("from EmployeeBiDi").list();
			for (Object obj : result) {
				EmployeeBiDi e = (EmployeeBiDi) obj;
				System.out.println("Name: " + e.getName() + ", Designation: " + e.getDesignation() + ", Salary: " + e.getSalary());
				for (Address addr : e.getAddresses()) {
					System.out.println("Address: " + addr.getPlotNo() + ", " + addr.getStreet() + ", " + addr.getLocation());
				}
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
