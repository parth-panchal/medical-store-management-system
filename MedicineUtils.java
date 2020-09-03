package medicine;

public class MedicineUtils
{
	private String MedicineID;
	private String MedicineName;
	private String MedicineType;
	private String CompanyName;
	private String Stock;
	private String MedicinePrice;
	private String expiryDate;

	public MedicineUtils()
	{
		//null constructor
	}
	
	public MedicineUtils(String MedicineID, String MedicineName, String MedicineType, String CompanyName, String Stock, String MedicinePrice, String expiryDate)
	{
		this.MedicineID = MedicineID;
		this.MedicineName = MedicineName;
		this.MedicineType = MedicineType;
		this.CompanyName = CompanyName;
		this.Stock = Stock;
		this.MedicinePrice = MedicinePrice;
		this.expiryDate = expiryDate;
	}
	
	public String getMedicineID()
	{
		return MedicineID;
	}

	public void setMedicineID(String MedicineID)
	{
		this.MedicineID = MedicineID;
	}

	public String getMedicineName()
	{
		return MedicineName;
	}

	public void setMedicineName(String MedicineName)
	{
		this.MedicineName = MedicineName;
	}

	public String getMedicineType()
	{
		return MedicineType;
	}

	public void setMedicineType(String MedicineType)
	{
		this.MedicineType = MedicineType;
	}

	public String getCompanyName()
	{
		return CompanyName;
	}

	public void setCompanyName(String CompanyName)
	{
		this.CompanyName = CompanyName;
	}

	public String getStock()
	{
		return Stock;
	}

	public void setStock(String Stock)
	{
		this.Stock = Stock;
	}

	public String getMedicinePrice()
	{
		return MedicinePrice;
	}

	public void setMedicinePrice(String MedicinePrice) {
		this.MedicinePrice = MedicinePrice;
	}

	public String getexpiryDate()
	{
		return expiryDate;
	}

	public void setexpiryDate(String expiryDate)
	{
		this.expiryDate = expiryDate;
	}
	
	//Override
	public String toString()
	{
		String result;
		result =  "\nMedicine ID\t=====>\t" + getMedicineID();
		result += "\nMedicine Name\t=====>\t" + getMedicineName();
		result += "\nMedicine Type\t=====>\t" + getMedicineType();
		result += "\nCompany Name\t=====>\t" + getCompanyName();
		result += "\nStock\t\t=====>\t" + getStock();
		result += "\nMedicine Price\t=====>\t" + getMedicinePrice();
		result += "\nExpiry Date\t=====>\t" + getexpiryDate() + "\n";
		return result;
	}
}