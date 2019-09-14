package apim.github.tutorial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address {

	@Id
	private int addressId;

	@Column
	private String plotNo;

	@Column
	private String street;

	@Column
	private String location;

	@Column
	private String city;

	@ManyToOne
	@JoinColumn(name = "eid")
	private EmployeeBiDi employee;

	public Address() {
	}

	public Address(int addressId, String plotNo, String street, String location, String city) {
		this.addressId = addressId;
		this.plotNo = plotNo;
		this.street = street;
		this.location = location;
		this.city = city;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getPlotNo() {
		return plotNo;
	}

	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public EmployeeBiDi getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeBiDi employee) {
		this.employee = employee;
	}

}
