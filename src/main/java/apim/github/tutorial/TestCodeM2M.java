package apim.github.tutorial;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestCodeM2M {

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
				.addAnnotatedClass(EmployeeM2M.class)
				.addAnnotatedClass(Project.class)
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
			EmployeeM2M emp1 = new EmployeeM2M(1, "John", "Coder", 50);
			EmployeeM2M emp2 = new EmployeeM2M(2, "Jenny", "Coder", 50);
			EmployeeM2M emp3 = new EmployeeM2M(3, "Jim", "Coder", 50);
			Project prj1 = new Project(1, "Development", "ATT");
			Project prj2 = new Project(2, "Testing", "OSP");
			Set<Project> projects1 = new HashSet<>();
			projects1.add(prj1);
			emp1.setProjects(projects1);
			Set<Project> projects2 = new HashSet<>();
			projects2.add(prj1);
			projects2.add(prj2);
			emp2.setProjects(projects2);
			Set<Project> projects3 = new HashSet<>();
			projects3.add(prj1);
			emp3.setProjects(projects3);
			session.save(emp1);
			session.save(emp2);
			session.save(emp3);
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
			List result = session.createQuery("from EmployeeM2M").list();
			for (Object obj : result) {
				EmployeeM2M e = (EmployeeM2M) obj;
				System.out.println("Name: " + e.getName() + ", Designation: " + e.getDesignation() + ", Salary: " + e.getSalary());
				for (Project prj : e.getProjects()) {
					System.out.println("Project of " + e.getName() + ": " + prj.getName() + ", " + prj.getClient());
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
