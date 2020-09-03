package medicine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ChoiceUtils 
{
	static OperationUtils objOperationUtils = new OperationUtils();
	static ValidationUtils objValidationUtils = new ValidationUtils();
	static Scanner input = new Scanner(System.in);
	static int choice = 0;
	static String searchString = "";
	public static void mainMenu()
	{
		try
		{
			objOperationUtils.mainMenuView();
			choice = input.nextInt();
			String newValue;
			String oldValue;
			if (choice > 0 && choice < 4)
			{
				System.out.println("\n***********************************************************\n" + 
						"Please select any one option from the following Sub Menu:\n" + 
						"***********************************************************");
				switch (choice) 
				{
					case 1: objOperationUtils.addUpdateViewSubMenuView();
						choice = input.nextInt();
						switch (choice)
						{
							case 1 : objOperationUtils.addRecord();
								break;
							case 2 : System.out.print("Please provide with the Medicine ID for updating record: ");
								searchString = input.next();
								if (objOperationUtils.searchByMedicineID(searchString, false, true))
								{
									objOperationUtils.updateSubMenuView();
									choice = input.nextInt();
									switch (choice)
									{
										case 1: oldValue = OperationUtils.objMedicineUtils.getMedicineName();
											System.out.print("Enter New Medicine Name : ");
											newValue = input.next();
											if (!newValue.matches("^[a-zA-Z0-9]*$"))
											{
												System.out.println("There shouldn't be any digit in the Medicine Name.");
											}
											else
											{
												newValue = objValidationUtils.capitalFirstLetter(newValue);
												objOperationUtils.updateRecord(searchString, oldValue, newValue);
											}
											break;
										case 2: oldValue = OperationUtils.objMedicineUtils.getMedicineType();
											System.out.print("Enter New Medicine Type : ");
											newValue = input.next();
											if (!newValue.matches("^[a-zA-Z]*$"))
											{
												System.out.println("There shouldn't be any digit in the medicine type.");
											}
											else
											{
												newValue = objValidationUtils.capitalFirstLetter(newValue);
												objOperationUtils.updateRecord(searchString, oldValue, newValue);
											}
											break;
										case 3: oldValue = OperationUtils.objMedicineUtils.getCompanyName();
											System.out.print("Enter New Company Name (Only Alphabets): ");
											newValue = input.next();
											if (!newValue.matches("^[A-Za-z]*$"))
											{
												System.out.println("There shouldn't be any digit in the Major Course.");
											}
											else
											{
												objOperationUtils.updateRecord(searchString, oldValue, newValue);
											}
											break;
										case 4: oldValue = OperationUtils.objMedicineUtils.getStock();
											System.out.print("Enter New Stock: ");
											newValue = input.next();
											if(newValue.length() == 0)
											{
												System.out.println("Please enter proper stock.");
											}
											else
											{
												objOperationUtils.updateRecord(searchString, oldValue, newValue);
											}
											break;
										case 5: oldValue = OperationUtils.objMedicineUtils.getMedicinePrice();
											System.out.print("Enter New Medicine Price: ");
											newValue = input.next();
											objOperationUtils.updateRecord(searchString, oldValue, newValue);
											break;
										case 6: oldValue = OperationUtils.objMedicineUtils.getexpiryDate();
											System.out.print("Enter updated expiry date : ");
											newValue = input.next();
											if (objValidationUtils.isThisDateValid(newValue))
											{
												objOperationUtils.updateRecord(searchString, oldValue, newValue);
											}
											break;
										case 0 : objOperationUtils.exitMenu();
											break;
										case 9 :System.out.println("\n"); 
											mainMenu();
											break;
										default:
											System.out.println("\n***********************************************************\n" + 
												"Please select proper option.\nEnter 1 to continue.\n" + 
												"***********************************************************");
											choice = input.nextInt();
											if (choice == 1)
												mainMenu();
											else
											{
												input.close();
												objOperationUtils.exitMenu();
											}		
									}
								}
								mainMenu();
								break;
							case 3 : objOperationUtils.viewRecords();
								objOperationUtils.readFromFile();
								break;
							case 0 : objOperationUtils.exitMenu();
								break;
							case 9 :System.out.println("\n"); 
								mainMenu();
								break;
							default:
								System.out.println("\n***********************************************************\n" + 
										"Please select proper option.\nEnter 1 to continue.\n" + 
										"***********************************************************");
								choice = input.nextInt();
								if (choice == 1)
									mainMenu();
								else
								{
									input.close();
									objOperationUtils.exitMenu();
								}		
						}
					break;
					case 2: objOperationUtils.sortSubMenuView();
						choice = input.nextInt();
						switch (choice)
						{
							case 1:  objOperationUtils.sortByMedicineName();
								break;
							case 2: objOperationUtils.sortByMedicinePrice();
								break;
							case 3: objOperationUtils.sortByExpiryDate();
								break;
							case 0: objOperationUtils.exitMenu();
								break;
							case 9: System.out.println("\n"); 
								mainMenu();
								break;
							default:
								System.out.println("\n***********************************************************\n" + 
										"Please select proper option.\nEnter 1 to continue.\n" + 
										"***********************************************************");
								choice = input.nextInt();
								if (choice == 1)
									mainMenu();
								else
								{
									input.close();
									objOperationUtils.exitMenu();
								}
						}
					case 3: objOperationUtils.searchSubMenuView();
					choice = input.nextInt();
					switch (choice)
					{
						case 1: System.out.print("Enter Medicine ID for Search: "); 
							searchString = input.next();
							objOperationUtils.searchByMedicineID(searchString, true, true);
							break;
						case 2: System.out.print("Enter medicine type for Search: "); 
							searchString = input.next();
							objOperationUtils.searchByMedicineType(searchString);
							break;
						case 3: System.out.print("Enter Company Name for Search: "); 
							searchString = input.next();
							objOperationUtils.searchByCompanyName(searchString);
							break;
						case 0: objOperationUtils.exitMenu();
							break;
						case 9: System.out.println("\n"); 
							mainMenu();
							break;
						default:
							System.out.println("\n***********************************************************\n" + 
									"Please select proper option.\nEnter 1 to continue.\n" + 
									"***********************************************************");
							choice = input.nextInt();
							if (choice == 1)
								mainMenu();
							else
							{
								input.close();
								objOperationUtils.exitMenu();
							}
					}
				}
			}
			else if (choice == 0)
			{
				input.close();
				objOperationUtils.exitMenu();
			}
			else
			{
				System.out.println("\n***********************************************************\n" + 
						"Please select proper option.\nEnter 1 to continue.\n" + 
						"***********************************************************");
				choice = input.nextInt();
				if (choice == 1)
					mainMenu();
				else
				{
					input.close();
					objOperationUtils.exitMenu();
				}
			}
		}
		catch (InputMismatchException ime)
		{
			System.out.println("Please provide proper number as input.");
		}
		catch(Exception e)
		{
			System.out.println("Exception from mainMenu: " + e.getMessage());
		}
	}
}
