package com.test.phonebook;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import com.example.phonebook.PhoneBook;

/**
 */
public class PhoneBookTest {

	@Test
	public void phonebookShouldHaveValues() throws Exception {
		PhoneBook phoneBook = new PhoneBook();
		Map<Integer, Map<String, Integer>> directoryMap = new HashMap<Integer, Map<String, Integer>>();
		Map<String, Integer> expectedPhonebookMap = new TreeMap<String, Integer>();
		Map<String, Integer> outputPhonebookMap = new TreeMap<String, Integer>();
		expectedPhonebookMap.put("Sam", 12345678);
		expectedPhonebookMap.put("Marshall", 77373473);
		expectedPhonebookMap.put("Malcolm", 98657337);
		outputPhonebookMap = phoneBook.createNewAddressBookEntry(outputPhonebookMap, 1, directoryMap, "Sam", 12345678);
		outputPhonebookMap = phoneBook.createNewAddressBookEntry(outputPhonebookMap, 1, directoryMap, "Marshall",
				77373473);
		outputPhonebookMap = phoneBook.createNewAddressBookEntry(outputPhonebookMap, 1, directoryMap, "Malcolm",
				98657337);
		assertEquals(expectedPhonebookMap, outputPhonebookMap);
	}

	@Test
	public void ShouldReturnUniqueEntriesInAddressBook() throws Exception {
		PhoneBook phoneBook = new PhoneBook();
		Map<Integer, Map<String, Integer>> directoryMap = new HashMap<Integer, Map<String, Integer>>();
		Map<String, Integer> expectedPhonebookMap = new TreeMap<String, Integer>();
		Map<String, Integer> outputPhonebookMap1 = new TreeMap<String, Integer>();
		Map<String, Integer> outputPhonebookMap2 = new TreeMap<String, Integer>();

		List<String> expectedOutputList = new ArrayList<String>();

		expectedOutputList.add("Malcolm");
		expectedOutputList.add("Sam");
		expectedOutputList.add("Jacob");
		expectedOutputList.add("Shane");

		List<String> actualOutputList = new ArrayList<String>();

		outputPhonebookMap1 = phoneBook.createNewAddressBookEntry(outputPhonebookMap1, 1, directoryMap, "Sam",
				12345678);
		outputPhonebookMap1 = phoneBook.createNewAddressBookEntry(outputPhonebookMap1, 1, directoryMap, "Marshall",
				77373473);
		outputPhonebookMap1 = phoneBook.createNewAddressBookEntry(outputPhonebookMap1, 1, directoryMap, "Malcolm",
				98657337);

		outputPhonebookMap2 = phoneBook.createNewAddressBookEntry(outputPhonebookMap2, 2, directoryMap, "Shane",
				3463463);
		outputPhonebookMap2 = phoneBook.createNewAddressBookEntry(outputPhonebookMap2, 2, directoryMap, "Marshall",
				2222412);
		outputPhonebookMap2 = phoneBook.createNewAddressBookEntry(outputPhonebookMap2, 2, directoryMap, "Jacob",
				574574);

		actualOutputList = phoneBook.compareAddressBookContacts(directoryMap, 1, 2);

		assertEquals(expectedOutputList, actualOutputList);
	}

	@Test
	public void ShouldReturnEntriesInEachAddressBook() throws Exception {
		PhoneBook phoneBook = new PhoneBook();
		Map<Integer, Map<String, Integer>> directoryMap = new HashMap<Integer, Map<String, Integer>>();
		Map<String, Integer> expectedPhonebookMap = new TreeMap<String, Integer>();
		Map<String, Integer> outputPhonebookMap1 = new TreeMap<String, Integer>();
		Map<String, Integer> outputPhonebookMap2 = new TreeMap<String, Integer>();
		Map<String, Integer> expectedAddressBookList1 = new TreeMap<String, Integer>();
		Map<String, Integer> expectedAddressBookList2 = new TreeMap<String, Integer>();

		Map<String, Integer> outputAddressBookList1 = new TreeMap<String, Integer>();
		Map<String, Integer> outputAddressBookList2 = new TreeMap<String, Integer>();

		expectedAddressBookList1.put("Malcolm", 98657337);
		expectedAddressBookList1.put("Marshall", 2222412);
		expectedAddressBookList1.put("Sam", 12345678);

		expectedAddressBookList2.put("Jacob", 574574);
		expectedAddressBookList2.put("Marshall", 2222412);
		expectedAddressBookList2.put("Shane", 3463463);

		outputPhonebookMap1 = phoneBook.createNewAddressBookEntry(outputPhonebookMap1, 1, directoryMap, "Sam",
				12345678);
		outputPhonebookMap1 = phoneBook.createNewAddressBookEntry(outputPhonebookMap1, 1, directoryMap, "Marshall",
				2222412);
		outputPhonebookMap1 = phoneBook.createNewAddressBookEntry(outputPhonebookMap1, 1, directoryMap, "Malcolm",
				98657337);

		outputPhonebookMap2 = phoneBook.createNewAddressBookEntry(outputPhonebookMap2, 2, directoryMap, "Shane",
				3463463);
		outputPhonebookMap2 = phoneBook.createNewAddressBookEntry(outputPhonebookMap2, 2, directoryMap, "Marshall",
				2222412);
		outputPhonebookMap2 = phoneBook.createNewAddressBookEntry(outputPhonebookMap2, 2, directoryMap, "Jacob",
				574574);

		outputAddressBookList1 = phoneBook.viewAddressBookContacts(directoryMap, outputAddressBookList1, 1);

		assertEquals(expectedAddressBookList1, outputAddressBookList1);

		outputAddressBookList2 = phoneBook.viewAddressBookContacts(directoryMap, outputAddressBookList2, 2);
		assertEquals(expectedAddressBookList2, outputAddressBookList2);
	}

}