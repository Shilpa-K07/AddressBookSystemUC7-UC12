import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.stream.Collectors; 
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class AddressBookDao {
	HashMap <String, PersonEntity> contactList = new HashMap<String, PersonEntity>();
	HashMap <String, PersonEntity> cityDictionary = new HashMap<String, PersonEntity>();
	HashMap <String, PersonEntity> stateDictionary = new HashMap<String, PersonEntity>();
	
	Scanner scanner = new Scanner(System.in);
	
	public void getUserChoice() {
		boolean isTerminate = false;
		while (!isTerminate) {
		System.out.println("Enter your option");
		System.out.println("1: Add new contact \n" +
				"2: Update contact \n" +
				"3: Delete contact \n" +
				"4: Search contacts by city \n" +
				"5: Search contacts by state \n" +
				"6: View contacts by city \n" +
				"7: View contacts by state \n" +
				"8: Sort by name \n" +
				"9: Sort by city \n" +
				"10: Sort by state \n" +
				"11: Sort by zip \n" +
				"12: Print address book \n"+
				"13: Exit \n");
		int option = scanner.nextInt();
		switch(option) {
			case 1:
				PersonEntity person = getUserInput();
				if(person != null) {
				contactList.put(person.getEmailId(), person);
				cityDictionary.put(person.getCity(), person);
				stateDictionary.put(person.getState(), person);
		 		System.out.println("Contact added !");
				}
		 		break;
			case 2:
				updateContact();
                break;
			case 3:
				deleteContact();
				break;
			case 4:
				searchByCity();
				break;
			case 5:
				seachByState();
				break;
			case 6:
				System.out.println(cityDictionary);
				break;
			case 7:
				System.out.println(stateDictionary);
				break;
			case 8:
				sortByName();
				break;
			case 9:
				sortByCity();
				break;
			case 10:
				sortByState();
				break;
			case 11:
				sortByZip();
				break;
			case 12:
				System.out.println(contactList);
				break;
			case 13:
				isTerminate = true;
				break;
			default:
				System.out.println("Please provide correct option");
				break;
			}
		}
	}
	
	private void sortByState() {
		Map<String,PersonEntity> sortedNewMap = contactList.entrySet().stream()
				.sorted((element1,element2)->element1.getValue().getState().compareTo(element2.getValue().getState()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
					(element1, element2) -> element1, LinkedHashMap::new));
		System.out.println("\nAfter sorting by state: ");
		System.out.println(sortedNewMap);
	}

	private void sortByCity() {
		Map<String,PersonEntity> sortedNewMap = contactList.entrySet().stream()
				.sorted((element1,element2)->element1.getValue().getCity().compareTo(element2.getValue().getCity()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
					(element1, element2) -> element1, LinkedHashMap::new));
		System.out.println("\nAfter sorting by city: ");
		System.out.println(sortedNewMap);
	}

	private void sortByZip() {
		Map<String,PersonEntity> sortedNewMap = contactList.entrySet().stream()
				.sorted((element1,element2)->element1.getValue().getZip()-(element2.getValue().getZip()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
					(element1, element2) -> element1, LinkedHashMap::new));
		System.out.println("\nAfter sorting by zip: ");
		System.out.println(sortedNewMap);
	}

	private void sortByName() {
		Map<String,PersonEntity> sortedNewMap = contactList.entrySet().stream()
				.sorted((element1,element2)->element1.getValue().getFirstName().compareTo(element2.getValue().getFirstName()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
					(element1, element2) -> element1, LinkedHashMap::new));
		System.out.println("\nAfter sorting by name: ");
		System.out.println(sortedNewMap);
	}

	private void seachByState() {
		System.out.println("Enter state name to search with ");
		String state = scanner.next();
		
		List<PersonEntity> personList = contactList.entrySet().stream()
				.filter( map -> map.getValue().getState().equals(state))
				.map(Map.Entry::getValue)
				.collect(Collectors.toList());
		System.out.println(personList);
		int personCount = contactList.entrySet().stream()
				.filter( map -> map.getValue().getState().equals(state))
				.map(Map.Entry::getValue)
				.collect(Collectors.toList()).size();
		System.out.println("Number of contacts in "+state+" state is: "+personCount);
	}

	private void searchByCity() {
		System.out.println("Enter city name to search with ");
		String city = scanner.next();
		
		List<PersonEntity> personList = contactList.entrySet().stream()
				.filter( map -> map.getValue().getCity().equals(city))
				.map(Map.Entry::getValue)
				.collect(Collectors.toList());
		System.out.println(personList);
		int personCount = contactList.entrySet().stream()
				.filter( map -> map.getValue().getCity().equals(city))
				.map(Map.Entry::getValue)
				.collect(Collectors.toList()).size();
		System.out.println("Number of contacts in "+city+" city is: "+personCount);
	}

	private boolean duplicateCheck(String emailId) {
		Optional<String> key = contactList.entrySet().stream()
				.filter( map -> map.getKey().equals(emailId))
				.map(Map.Entry::getKey).findAny();
		boolean result = key.isPresent();
		return result;
	}
	
	private void deleteContact() {
        System.out.println("Enter EmailId to delete");
        String emailId = scanner.next();
        if (!contactList.containsKey(emailId)) {
            System.out.println("Provided email Id is not found");
            deleteContact();
        }
        contactList.remove(emailId);
        System.out.println("Contact deleted !");
    }

	private void updateContact() {
		System.out.println("Enter emailId to update");
		String emailId = scanner.next();

		if (!contactList.containsKey(emailId)) {
			System.out.println("Email Id provided is wrong !");
			updateContact();
        }
		
		PersonEntity person = contactList.get(emailId);
		boolean isTerminate = false;
		while(!isTerminate) {
			System.out.println("Which field do you want to update?");
			System.out.println("1: First name \n" +
	                "2: Last Name \n" +
	                "3: Address \n" +
	                "4: City \n" +
	                "5: State \n" +
	                "6: Zip \n" +
	                "7: PhoneNumber \n" +
	                "8: Exit \n");

	        int option = scanner.nextInt();
	        switch (option) {
	            case 1:
	                System.out.println("Enter First Name ");
	                String firstName = scanner.next();
	                person.setFirstName(firstName);
	                break;
	            case 2:
	                System.out.println("Enter Last Name");
	                String lastName = scanner.next();
	                person.setLastName(lastName);
	                break;
	            case 3:
	                System.out.println("Enter Address");
	                String address = scanner.next();
	                person.setAddress(address);
	                break;
	            case 4:
	                System.out.println("Enter City");
	                String city = scanner.next();
	                person.setCity(city);
	                break;
	            case 5:
	                System.out.println("Enter State");
	                String state = scanner.next();
	                person.setState(state);
	                break;
	            case 6:
	                System.out.println("Enter Zip Code");
	                int zip=scanner.nextInt();
	                person.setZip(zip);
	                break;
	            case 7:
	                System.out.println("Enter Phone Number");
	                long phone = scanner.nextLong();
	                person.setPhoneNumber(phone);
	                break;
	            case 8:
	            	System.out.println("Exiting..");
	            	isTerminate = true;
	            default:
	                System.out.println("please select valid option");
	                break;
	        }
	        System.out.println("Details updated!\n");
		}
	}

	private PersonEntity getUserInput() {
		System.out.println("Enter First Name");
		String firstName = scanner.next();
		System.out.println("Enter Last Name");
		String lastName = scanner.next();
		System.out.println("Enter Address");
		String address = scanner.next();
		System.out.println("Enter City Name");
		String city = scanner.next();
		System.out.println("Enter the Zip Code");
		int zip = scanner.nextInt();
		System.out.println("Enter State Name");
		String state = scanner.next();
		System.out.println("Enter Phone Number");
		long phone = scanner.nextLong();
		System.out.println("Enter Email Id");
		String emailId = scanner.next();
		
		boolean result = duplicateCheck(emailId);
		if(result) {
			System.out.println("\nContact with this email is already exists"
					+ " in this address book");
			return null;
		}
		PersonEntity person = new PersonEntity();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setAddress(address);
		person.setCity(city);
		person.setZip(zip);
		person.setState(state);
		person.setPhoneNumber(phone);
		person.setEmailId(emailId);
		
		return person;
	}
}
