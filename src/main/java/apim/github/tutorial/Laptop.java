package apim.github.tutorial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Laptop {

	@Id
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "employee"))
	@GeneratedValue(generator = "generator")
	@Column(name = "eid")
	private int employeeId;

	@Column
	private String brand;

	@OneToOne
	@PrimaryKeyJoinColumn
	private EmployeeO2O employee;
	
	public Laptop() {
	}

	public Laptop(String brand) {
		this.brand = brand;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public EmployeeO2O getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeO2O employee) {
		this.employee = employee;
	}

}
