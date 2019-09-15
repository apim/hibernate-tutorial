package apim.github.tutorial;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee_many_to_many")
public class EmployeeM2M {

	@Id
	private int id;

	@Column
	private String name;

	@Column
	private String designation;

	@Column
	private int salary;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "emp_prj", joinColumns = @JoinColumn(name = "eid"), inverseJoinColumns = @JoinColumn(name = "pid"))
	private Set<Project> projects;

	public EmployeeM2M() {
	}

	public EmployeeM2M(int id, String name, String designation, int salary) {
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
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

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

}
