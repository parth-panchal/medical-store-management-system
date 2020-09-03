package medicine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils 
{
	MedicineUtils objMedicineUtils = new MedicineUtils();
	OperationUtils objOperationUtils = new OperationUtils();
	
	public boolean validateChoice(int value)
	{
		return true;
	}
	
	public boolean isNumeric(int value)
	{
		return (Integer.toString(value)).matches("\\d");
	}

	public boolean validateData(String MedicineID, String MedicineName, String MedicineType, String CompanyName, String Stock, String MedicinePrice, String expiryDate)
	{
		if(objOperationUtils.searchByMedicineID(MedicineID, false, false))
		{
			System.out.println("Medicine Record already exsists for: " + MedicineID);
			return false;
		}
		else 
		{
			if (!MedicineID.matches("^MD(\\d{6})$"))
			{
				System.out.println("Please enter a valid Medicine ID (MDxxxxxx).");
				return false;
			}
			else if (!MedicineName.matches("^[a-zA-Z0-9]*$"))
			{
				System.out.println("There shouldn't be any digit in the medicine name.");
				return false;
			}
			else if (!MedicineType.matches("^[a-zA-Z]*$"))
			{
				System.out.println("There shouldn't be any digit in the medicine type.");
				return false;
			}
			else if (!CompanyName.matches("^[a-zA-Z]*$"))
			{
				System.out.println("There shouldn't be any digit in the Company Name.");
				return false;
			}
			else if(Stock.length() == 0)
			{
				System.out.println("Please enter proper stock.");
				return false;
			}
			return true;
		}
	}

	public String StockFormat(String Stock) 
	{
		return Stock;
	}
	
	public boolean isThisDateValid(final String date)
	 {
		final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(DATE_PATTERN);
		matcher = pattern.matcher(date);
		if(matcher.matches())
		{
			matcher.reset();
			if(matcher.find())
			{
				String day = matcher.group(1);
				String month = matcher.group(2);
				int year = Integer.parseInt(matcher.group(3));
				if (day.equals("31") && (month.equals("4") || month.equals("6") || month.equals("9") ||
                 month.equals("11") || month.equals("04") || month.equals("06") ||
                 month.equals("09")))
				{
					System.out.println("Please enter valid expiry date in proper format (DD/MM/YYYY).");
					return false; // only 1,3,5,7,8,10,12 has 31 days
				}
				else if (month.equals("2") || month.equals("02"))
				{
					//leap year
					if(year % 4==0)
					{
						if(day.equals("30") || day.equals("31"))
						{
							System.out.println("Please enter valid expiry date in proper format (DD/MM/YYYY).");
							return false;
						}
						else
						{
							return true;
						}
					}
					else
					{
						if(day.equals("29")||day.equals("30")||day.equals("31"))
						{
							System.out.println("Please enter valid expiry date in proper format (DD/MM/YYYY).");
							return false;
						}
						else
						{
							return true;
						}
					}
				}
				else
				{				 
					return true;				 
				}
			}
			else
			{
				System.out.println("Please enter valid expiry date in proper format (DD/MM/YYYY).");
				return false;
			}		  
		}
		else
		{
			System.out.println("Please enter valid expiry date in proper format (DD/MM/YYYY).");
			return false;
		}
	 }
	
	public String capitalFirstLetter(String value)
	{
		return value.substring(0, 1).toUpperCase() + value.substring(1);
	}
}

