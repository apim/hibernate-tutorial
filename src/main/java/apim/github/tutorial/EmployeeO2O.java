package apim.github.tutorial;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_one_to_one")
public class EmployeeO2O {

	@Id
	private int id;

	@Column
	private String name;

	@Column
	private String designation;

	@Column
	private int salary;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
	private Laptop laptop;

	public EmployeeO2O() {
	}

	public EmployeeO2O(int id, String name, String designation, int salary, Laptop laptop) {
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
		this.laptop = laptop;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Laptop getLaptop() {
		return laptop;
	}

	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}

}
