package countryKeyFiguresTest.shared;

public class ConfigRow {
	private String Key;
	private String Value;
	
	public ConfigRow(String Key, String Value){
		this.Key=Key;
		this.Value=Value;
	}
	
	public String getKey(){
		return Key;
	}
	public String getValue(){
		return Value;
	}
	
	public void setKey(String Key){
		this.Key=Key;
	}
	public void setValue(String Value){
		this.Value=Value;
	}
}
