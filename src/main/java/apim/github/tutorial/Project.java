package apim.github.tutorial;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Project {

	@Id
	@Column
	private int projectId;

	@Column
	private String name;

	@Column
	private String client;

	@ManyToMany(mappedBy = "projects")
	private Set<EmployeeM2M> employees;

	public Project() {
	}

	public Project(int projectId, String name, String client) {
		this.projectId = projectId;
		this.name = name;
		this.client = client;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Set<EmployeeM2M> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeM2M> employees) {
		this.employees = employees;
	}

}
