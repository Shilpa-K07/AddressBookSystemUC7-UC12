public class PersonEntity {
String firstName;
String lastName;
String address;
String city;
String state;
int zip;
Long phoneNumber;
String email;

public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public int getZip() {
	return zip;
}
public void setZip(int zip) {
	this.zip = zip;
}
public Long getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(Long phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getEmailId() {
	return email;
}
public void setEmailId(String email) {
	this.email = email;
}

@Override
public String toString() {
	return "PersonEntity [firstName=" + firstName + ", "
			+ "lastName=" + lastName + 
			", address=" + address + 
			", city=" + city
			+ ", state=" + state + 
			", zip=" + zip + ", phoneNumber=" + phoneNumber + 
			", email=" + email + "]";
}
@Override
public boolean equals(Object object) {
	PersonEntity person = (PersonEntity)object;
	return this.email.equals(person.email);
}
}
