package medicine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class OperationUtils
{
	static MedicineUtils objMedicineUtils = new MedicineUtils();
	static ValidationUtils objValidationUtils = new ValidationUtils();
	static final String filePath = System.getProperty("user.dir")+"\\";
	static final String fileName = "MedicineData.txt";
	//static final String fileName = "MedicineData.dat";
	String MedicineName = "";

	public void mainMenuView()
	{
		System.out.println("***********************************************************\n" +
				"Please select any one option from the Main Menu: \n" +
				"***********************************************************\n" +
				"\t1. Add New/Update/View Medicine Record(s)\n" +
				"\t2. Sort Medicine Record(s)\n" +
				"\t3. Search for Medicine Record(s)\n" +
				"\t0. Exit\n");
		System.out.print("Enter your choice: ");
	}

	public void addUpdateViewSubMenuView()
	{
		System.out.println("\t1. Add Medicine Record\n" +
				   "\t2. Update Medicine Record\n" +
				   "\t3. View Medicine Record(s)\n" +
				   "\t0. Exit\n" +
				   "\t9. For Main Menu\n");
		System.out.print("Enter your choice: ");
	}

	public void updateSubMenuView()
	{
		System.out.println("\n***********************************************************\n" +
				"Which information will you like to modify?\n"+
				"Please select any one option from the following Sub Menu:\n" +
				"***********************************************************\n"+
				"\t1. Update Medicine Name\n" +
				"\t2. Update Medicine Type\n" +
				"\t3. Update Company Name\n" +
				"\t4. Update Stock\n" +
				"\t5. Update Medicine Price\n" +
				"\t6. Update Expiry Date\n" +
				"\t0. Exit\n" +
				"\t9. For Main Menu\n");
		System.out.print("Enter your choice: ");
	}

	public void sortSubMenuView()
	{
		System.out.println("\t1. Sort By Medicine Name\n" +
				   "\t2. Sort By Medicine Price\n" +
				   "\t3. Sort By Expiry Date\n" +
				   "\t0. Exit\n" +
				   "\t9. For Main Menu\n");
		System.out.print("Enter your choice: ");
	}

	public void searchSubMenuView()
	{
		System.out.println("\t1. Search By Medicine ID\n" +
				   "\t2. Search By Medicine Type \n" +
				   "\t3. Search By Company Name\n" +
				   "\t0. Exit\n" +
				   "\t9. For Main Menu\n");
		System.out.print("Enter your choice: ");
	}

	//This function will add Medicine records to the file.
	public void addRecord()
	{
		Scanner info = new Scanner(System.in);
		try
		{
			System.out.print("Enter Medicine ID (MDxxxxxx): ");
			String MedicineID = info.next();
			objMedicineUtils.setMedicineID(MedicineID);

			System.out.print("Enter Medicine Name : ");
			String MedicineName = info.next();
			objMedicineUtils.setMedicineName(MedicineName);

			System.out.print("Enter Medicine Type : ");
			String MedicineType = info.next();
			objMedicineUtils.setMedicineType(MedicineType.toUpperCase());

			System.out.print("Enter Company Name : ");
			String CompanyName = info.next();
			objMedicineUtils.setCompanyName(CompanyName.toUpperCase());

			System.out.print("Enter Medicine Stock : ");
			String Stock = info.next();
			objMedicineUtils.setStock(Stock);

			System.out.print("Enter Medicine Price : ");
			String MedicinePrice = info.next();
			objMedicineUtils.setMedicinePrice(MedicinePrice);

			System.out.print("Enter Medicine expiry date (DD/MM/YYYY): ");
			String expiryDate = info.next();
			objMedicineUtils.setexpiryDate(expiryDate);

			if (objValidationUtils.validateData(objMedicineUtils.getMedicineID(), objMedicineUtils.getMedicineName(), objMedicineUtils.getMedicineType(), objMedicineUtils.getCompanyName(), objMedicineUtils.getStock(), objMedicineUtils.getMedicinePrice(), objMedicineUtils.getexpiryDate())
					&& objValidationUtils.isThisDateValid(objMedicineUtils.getexpiryDate()))
			{
				objMedicineUtils.setStock(objValidationUtils.StockFormat(objMedicineUtils.getStock()));
				objMedicineUtils.setMedicineName(objValidationUtils.capitalFirstLetter(objMedicineUtils.getMedicineName()));
				objMedicineUtils.setCompanyName(objValidationUtils.capitalFirstLetter(objMedicineUtils.getCompanyName()));
				if (writeToFile())
				{
					System.out.println("\n***********************************************************\n" +
					"Recorded added successfully for "  + objMedicineUtils.getMedicineID() + " " + objMedicineUtils.getMedicineName() + "...!!!\n" +
					"***********************************************************\n");
					ChoiceUtils.mainMenu();

				}
				else
				{
					System.out.println("\n***********************************************************" +
					"Recorded was not successfully added for "  + objMedicineUtils.getMedicineID() + " " + objMedicineUtils.getMedicineName() + "...!!!\n" +
					"***********************************************************\n");
					ChoiceUtils.mainMenu();
				}
			}
			else
			{
				ChoiceUtils.mainMenu();
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception from addRecord: " + e.getMessage());
		}
		finally
		{
			info.close();
		}
	}

	//This function will update the existing Medicine records to the file.
	public void updateRecord(String MedicineID, String oldValue, String newValue)
	{
		try
		{
			FileReader fileReader = new FileReader(filePath + fileName);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			String line;
	        StringBuffer input = new StringBuffer();
	        boolean isReplaced = false;
	        while ((line = bufferReader.readLine()) != null)
	        {
	        	//System.out.println(input); // check that it's inputed right
		        //System.out.println(line.matches(".*" + MedicineID + ",.*" + oldValue + ".*"));

		        if (line.matches(".*" + MedicineID + ",.*" + oldValue + ".*"))
		        {
		        	String formattedValue = objValidationUtils.StockFormat(newValue);
		        	if (!oldValue.equals(formattedValue))
		        	{
		        		line = line.replace(oldValue, formattedValue);
		        		isReplaced = true;
		        	}
		        }
		        input.append(line + "\n");
	        }
			if (isReplaced)
			{
			// write the new String with the replaced line OVER the same file
				FileOutputStream fos = new FileOutputStream(filePath + fileName);
				fos.write(input.toString().getBytes());
				System.out.println("\n*****************************************" +
						"\nRecord updated for Medicine ID: " + MedicineID +
						"\n*****************************************");
				searchByMedicineID(MedicineID, false, true);
				fos.close();
			}
			else
			{
				System.out.println("\n*****************************************" +
						"\nNo Record updated for Medicine ID: " + MedicineID +
						"\n*****************************************");
			}
			bufferReader.close();
			fileReader.close();

			ChoiceUtils.mainMenu();
	    }
		catch (Exception e)
		{
			System.out.println("Exception from updateRecord: " + e.getMessage());
		}
	}

	//This function will view all Medicines records present in the file.
	public void viewRecords()
	{
		try
		{
			ArrayList<MedicineUtils> MedicineList = getAllMedicineData(true);
			if(MedicineList.size() != 0)
			{
				System.out.println("\n*******************************" +
				"\nTotal Record(s) found: " + MedicineList.size() +
				"\n*******************************");
				for (int i = 0; i < MedicineList.size(); i++)
				{
					objMedicineUtils = MedicineList.get(i);
					System.out.println("*******************************\n" + (i + 1) + ") Record for Medicine: " + MedicineList.get(i).getMedicineID() +
							"\n********************************" +
							objMedicineUtils.toString());
				}
			}
			ChoiceUtils.mainMenu();
		}
		catch (Exception e)
		{
			System.out.println("Exception from updateRecord: " + e.getMessage());
		}
	}

	//This function will exit user from the application.
	public void exitMenu()
	{
		System.out.println("\n***********************************************************\n" +
				"Exiting, Thank you for using this application...!!!\n" +
				"***********************************************************");
		System.exit(0);
	}

	// Appending Medicine data to the file.
	public boolean writeToFile()
	{
		try
		{
			FileWriter fileWriter = new FileWriter(filePath + fileName, true);
			PrintWriter outputFile = new PrintWriter(fileWriter);

			outputFile.println(objMedicineUtils.getMedicineID() + ", " + objMedicineUtils.getMedicineName() + ", " + objMedicineUtils.getMedicineType() +
					", " + objMedicineUtils.getCompanyName() + ", " + objMedicineUtils.getStock() +
					", " + objMedicineUtils.getMedicinePrice() + ", " + objMedicineUtils.getexpiryDate());

			outputFile.close();
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception from writeToFile" + e.getMessage());
			return false;
		}
	}

	public ArrayList<MedicineUtils> getAllMedicineData(boolean displayNoRecordFound)
	{
		try
		{
			File fileRead = new File(filePath + fileName);
		    Scanner lineScanner = new Scanner(fileRead);
		    //ArrayList<String> MedicineData = new ArrayList<String>();
		    ArrayList<MedicineUtils> MedicinesArrayList = new ArrayList<MedicineUtils>();
			 // Read lines from the file until no more are left.
			 while (lineScanner.hasNext())
			 {
				 // Read the next name.
				 String MedicineInfo = lineScanner.nextLine();

				 // Display the last name read.
				 String[] MedicineData = MedicineInfo.split(",");

				 MedicinesArrayList.add(new MedicineUtils(MedicineData[0].trim(), MedicineData[1].trim(), MedicineData[2].trim(), MedicineData[3].trim(), MedicineData[4].trim(), MedicineData[5].trim(), MedicineData[6].trim()));
			 }

			 // Close the file.
			 lineScanner.close();
			 if (MedicinesArrayList.size() != 0)
			 {
				 return MedicinesArrayList;
			 }
			 else if (displayNoRecordFound)
			 {
				 System.out.println("*****************************\nSorry, No Record Found...!!!" +
							"\n*****************************");
				 ChoiceUtils.mainMenu();
				 return null;
			 }
		}
		catch(Exception e)
		{
			System.out.println("Exception from readFromFile: " + e.getMessage());
			return null;
		}
		return null;
	}

	public void readFromFile()
	{
		try
		{
			FileInputStream fileInputStream = new FileInputStream(filePath + fileName);
		    ObjectInputStream objInputFile = new ObjectInputStream(fileInputStream);

			MedicineUtils[] MedicineList = (MedicineUtils[])objInputFile.readObject();

			for(int i = 0; i < MedicineList.length; i++)
			{
				System.out.println(MedicineList[i]);
			}
			objInputFile.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception from readFromFile: " + e);
		}

	}
	public void sortByMedicinePrice()
	{
		try
		{
			MedicineUtils temp;
			ArrayList<MedicineUtils> MedicineList = getAllMedicineData(true);
			if(MedicineList != null)
			{
				for(int i = 0; i < MedicineList.size() - 1; i++)
				{
					for(int j = i + 1; j < MedicineList.size(); j++)
					{
						if(MedicineList.get(i).getMedicinePrice().compareTo(MedicineList.get(j).getMedicinePrice()) > 0)
						{
							temp = MedicineList.get(i);
							MedicineList.set(i, MedicineList.get(j));
							MedicineList.set(j, temp);
						}
					}
				}

				for (int i = 0; i < MedicineList.size(); i++)
				{
					objMedicineUtils = MedicineList.get(i);
					System.out.println("*******************************\n" + (i + 1) + ") Record for Medicine: " + MedicineList.get(i).getMedicineID() +
							"\n********************************" +
							objMedicineUtils.toString());
				}
			}
			else
			{
				System.out.println("*****************************\nSorry, No Record Found...!!!\n" +
						"\n*****************************");
			}
			ChoiceUtils.mainMenu();
		}
		catch (Exception e)
		{
			System.out.println("Exception from sortByMedicineType: " + e.getMessage());
		}

	}

	public void sortByMedicineName()
	{
		try
		{
			MedicineUtils temp;
			ArrayList<MedicineUtils> MedicineList = getAllMedicineData(true);
			if(MedicineList != null)
			{
				for(int i = 0; i < MedicineList.size() - 1; i++)
				{
					for(int j = i + 1; j < MedicineList.size(); j++)
					{
						if(MedicineList.get(i).getMedicineName().compareTo(MedicineList.get(j).getMedicineName()) > 0)
						{
							temp = MedicineList.get(i);
							MedicineList.set(i, MedicineList.get(j));
							MedicineList.set(j, temp);
						}
					}
				}

				for (int i = 0; i < MedicineList.size(); i++)
				{
					objMedicineUtils = MedicineList.get(i);
					System.out.println("*******************************\n" + (i + 1) + ") Record for Medicine: " + MedicineList.get(i).getMedicineID() +
							"\n********************************" +
							objMedicineUtils.toString());
				}
			}
			else
			{
				System.out.println("*****************************\nSorry, No Record Found...!!!\n" +
						"\n*****************************");
			}
			ChoiceUtils.mainMenu();
		}
		catch (Exception e)
		{
			System.out.println("Exception from sortByMedicineType: " + e.getMessage());
		}
	}

	public void sortByExpiryDate()
	{
		try
		{
			MedicineUtils temp;
			ArrayList<MedicineUtils> MedicineList = getAllMedicineData(true);
			if(MedicineList != null)
			{
				for(int i = 0; i < MedicineList.size() - 1; i++)
				{
					for(int j = i + 1; j < MedicineList.size(); j++)
					{
						if(MedicineList.get(i).getexpiryDate().compareTo(MedicineList.get(j).getexpiryDate()) > 0)
						{
							temp = MedicineList.get(i);
							MedicineList.set(i, MedicineList.get(j));
							MedicineList.set(j, temp);
						}
					}
				}

				for (int i = 0; i < MedicineList.size(); i++)
				{
					objMedicineUtils = MedicineList.get(i);
					System.out.println("*******************************\n" + (i+1) + ") Record for Medicine: " + MedicineList.get(i).getMedicineID() +
							"\n********************************" +
							objMedicineUtils.toString());
				}
			}
			else
			{
				System.out.println("*****************************\nSorry, No Record Found...!!!\n" +
						"\n*****************************");
			}
			ChoiceUtils.mainMenu();
		}
		catch (Exception e)
		{
			System.out.println("Exception from sortByMedicineType: " + e.getMessage());
		}
	}

	public boolean searchByMedicineID(String MedicineID, boolean isCalledFromSearch, boolean displayNoRecordFound)
	{
		boolean found = false;
		try
		{
			ArrayList<MedicineUtils> MedicineList = getAllMedicineData(displayNoRecordFound);
			ArrayList<MedicineUtils> searchList = new ArrayList<MedicineUtils>();
			if(MedicineList != null)
			{
				for(int i = 0; i < MedicineList.size(); i++)
				{
					if(MedicineList.get(i).getMedicineID().equals(MedicineID))
					{
						found = true;
						searchList.add(MedicineList.get(i));
					}
				}
				if(found)
				{
					for(int i = 0; i < searchList.size(); i++)
					{
						System.out.println("Total Record(s) Found: " + searchList.size());
						objMedicineUtils = searchList.get(i);
						System.out.println("*****************************\n"+ (i + 1) + ") Record Found for: " + MedicineID +
								"\n*****************************" +
								objMedicineUtils.toString());
					}
				}
				else if (displayNoRecordFound)
				{
					System.out.println("*****************************\nSorry, No Record Found for: " + MedicineID +
							"\n*****************************");
				}
			}
			if (isCalledFromSearch)
			{
				ChoiceUtils.mainMenu();
				return found;
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception from searchByMedicineID: " + e.getMessage());
		}
		return found;
	}

	public void searchByMedicineType(String MedicineType)
	{
		try
		{
			boolean found = false;

			ArrayList<MedicineUtils> MedicineList = getAllMedicineData(true);
			ArrayList<MedicineUtils> searchList = new ArrayList<MedicineUtils>();
			if(MedicineList != null)
			{
				for(int i = 0; i < MedicineList.size(); i++)
				{
					if(MedicineList.get(i).getMedicineType().equals(MedicineType))
					{
						found = true;
						searchList.add(MedicineList.get(i));
					}
				}
				if(found)
				{
					for(int i = 0; i < searchList.size(); i++)
					{
						System.out.println("Total Record(s) Found: " + searchList.size());
						objMedicineUtils = searchList.get(i);
						System.out.println("*****************************\n"+ (i + 1) + ") Record Found for: " + MedicineType +
								"\n*****************************" +
								objMedicineUtils.toString());
					}
				}
				else
				{
					System.out.println("*****************************\nSorry, No Record Found for: " + MedicineType +
							"\n*****************************");
				}
			}
			ChoiceUtils.mainMenu();
		}
		catch (Exception e)
		{
			System.out.println("Exception from searchByMedicineID: " + e.getMessage());
		}
	}

	public void searchByCompanyName(String CompanyName)
	{
		try
		{
			boolean found = false;

			ArrayList<MedicineUtils> MedicineList = getAllMedicineData(true);
			ArrayList<MedicineUtils> searchList = new ArrayList<MedicineUtils>();
			if(MedicineList != null)
			{
				for(int i = 0; i < MedicineList.size(); i++)
				{
					if(MedicineList.get(i).getCompanyName().equals(CompanyName))
					{
						found = true;
						searchList.add(MedicineList.get(i));
					}
				}
				if(found)
				{
					for(int i = 0; i < searchList.size(); i++)
					{
						System.out.println("Total Record(s) Found: " + searchList.size());
						objMedicineUtils = searchList.get(i);
						System.out.println("*****************************\n"+ (i + 1) + ") Record Found for: " + CompanyName +
								"\n*****************************" +
								objMedicineUtils.toString());
					}
				}
				else
				{
					System.out.println("*****************************\nSorry, No Record Found for: " + CompanyName +
							"\n*****************************");
				}
			}
			ChoiceUtils.mainMenu();
		}
		catch (Exception e)
		{
			System.out.println("Exception from searchByMedicineID: " + e.getMessage());
		}
	}
}