package com.example.phonebook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class PhoneBook {

	public static void main(String[] args) throws IOException, PhoneBookException {
		Scanner inputScanner = new Scanner(System.in);
		int menu = 0;
		boolean quit = false;
		int count = 0;
		Map<String, Integer> phonebookMap = null;
		Map<Integer, Map<String, Integer>> directoryMap = null;
		Map<String, Integer> AddressBookList = new TreeMap<String, Integer>();
		directoryMap = new HashMap<Integer, Map<String, Integer>>();
		try {
			do {
				System.out.println("Address Book Management");
				System.out.println("***************************************");
				System.out.println("1. Create a New Address Book");
				System.out.println("2. Enter Contact Details");
				System.out.println("3. Display All Contacts in the Address Book");
				System.out.println("4. Compare Unique Entries Between 2 Address Books");
				System.out.println("5. Exit");
				System.out.print("Please enter your choice: ");
				menu = inputScanner.nextInt();
				System.out.println();
				switch (menu) {
				case 1: // Create New Address Book
					count++;
					phonebookMap = new TreeMap<>();
					System.out.println("Address Book created with Id : " + count);
					break;
				case 2: // Add Contacts to Address Book
					loop1:
					if (phonebookMap == null) {
						System.out.println(
								"No Address Book Exists. Create a New Address Book and then add contacts to it");
						break;
					}
					System.out.print("Enter Name (only Alphabets) : ");
					String name = inputScanner.next();
					System.out.println("Enter Phone No (only Numbers, Limit 10) : ");
					int phoneNumber = inputScanner.nextInt();
					createNewAddressBookEntry(phonebookMap, count, directoryMap, name, phoneNumber);
					break;
				case 3: // View Contacts in Each Address Book
					System.out.println("Enter Address Book Id to View List of Contacts: ");
					int addressBookIndex = inputScanner.nextInt();
					viewAddressBookContacts(directoryMap, AddressBookList, addressBookIndex);
					break;
				case 4: // Compare Address Books and print Unique Contacts
					System.out.println("Enter an Address Book Id ");
					int addressBookInput1 = inputScanner.nextInt();
					System.out.println("Enter an Address Book Id to compare with ");
					int addressBookInput2 = inputScanner.nextInt();
					compareAddressBookContacts(directoryMap, addressBookInput1, addressBookInput2);
					break;
				case 5:
					quit = true;
					break;
				default:
					System.out.println("Invalid Entry!");
				}
			} while (!quit);
		} catch (InputMismatchException inputMismatchException) {
			throw new PhoneBookException("Input Provided in Improper Format. Re-Run and Try Again");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param phonebookMap
	 * @param count
	 * @param directoryMap
	 * @param name
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Integer> createNewAddressBookEntry(Map<String, Integer> phonebookMap, int count,
			Map<Integer, Map<String, Integer>> directoryMap, String name, int phoneNumber) throws Exception {
		phonebookMap.put(name, phoneNumber);
		directoryMap.put(count, phonebookMap);
		System.out.println("Contact Details Added to Address Book : " + count);
		return phonebookMap;

	}

	/**
	 * @param directoryMap
	 * @param addressBookList
	 * @param addressBookIndex
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Integer> viewAddressBookContacts(Map<Integer, Map<String, Integer>> directoryMap,
			Map<String, Integer> addressBookList, int addressBookIndex) throws Exception {
		if (directoryMap == null || (directoryMap != null && directoryMap.isEmpty())) {
			System.out.println("There are no active address books.");
		} else if (addressBookIndex > directoryMap.size()) {
			System.out.println("No Such address Book exists");
		} else {
			addressBookList = directoryMap.get(addressBookIndex);
			addressBookList.entrySet().forEach(entry -> {
				System.out.println("Name : " + entry.getKey() + " PhoneNumber : " + entry.getValue());
			});
		}
		return addressBookList;
	}

	/**
	 * @param directoryMap
	 * @param addressBookInput1
	 * @param addressBookInput2
	 * @return
	 * @throws Exception
	 */
	public static List<String> compareAddressBookContacts(Map<Integer, Map<String, Integer>> directoryMap,
			int addressBookInput1, int addressBookInput2) throws Exception {

		if (directoryMap == null || (directoryMap != null && directoryMap.isEmpty())) {
			System.out.println("There are no active address books to be compared");
			return null;
		}

		if (addressBookInput1 > directoryMap.size() || addressBookInput2 > directoryMap.size()) {
			System.out.println("No AddressBook with such indices exist. Please enter proper values");
			return null;
		}

		final List<String> uniqueContactsPhoneList = new ArrayList<String>();

		Map<String, Integer> inputAddressBookHashMap1 = new HashMap<String, Integer>();
		Map<String, Integer> inputAddressBookHashMap2 = new HashMap<String, Integer>();

		inputAddressBookHashMap1 = directoryMap.get(addressBookInput1);
		inputAddressBookHashMap2 = directoryMap.get(addressBookInput2);

		for (final String key : inputAddressBookHashMap1.keySet()) {
			if (!inputAddressBookHashMap2.containsKey(key)) {
				uniqueContactsPhoneList.add(key);
			}
		}

		for (final String key : inputAddressBookHashMap2.keySet()) {
			if (!inputAddressBookHashMap1.containsKey(key)) {
				uniqueContactsPhoneList.add(key);
			}
		}
		System.out.println("Unique Contacts between both address books");
		System.out.println("*******************************************");
		uniqueContactsPhoneList.forEach(item -> System.out.println(item));
		return uniqueContactsPhoneList;
	}

}
