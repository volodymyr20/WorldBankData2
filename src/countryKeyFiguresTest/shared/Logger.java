// 1) Outputs a message in into stdout with a timestamp
// 2) Outputs certain key values for a list of countries into either a) stdout, or b) file 

package countryKeyFiguresTest.shared;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {
	
	public void LogMessage(String Message, String OutputType){
		String now = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		
		if (OutputType == "stdout") System.out.println(Message + ": " + now);	
		if (OutputType == "file") { //TBD
		}
	}	

	public void LogResults(String[][] Countries, String ColumnName, String OutputType){
		
		String Header="Country", OutpuLine, Delimiter=",", Enclosure="\"", FileName="Country_";
		
		Writer writer = null;
		
		Header+=Delimiter+ColumnName;
		
		int i, ColumnIndex=1;
		
		switch (ColumnName) {
        case "GDP":  		ColumnIndex = 1;
            break;
        case "Population":  ColumnIndex = 2;
        	break;
        case "CO2":  		ColumnIndex = 3;
        	break;
		}
		
		if (OutputType == "stdout") System.out.println(Header);
		
		if (OutputType == "file") {	
			
			FileName += ColumnName + ".csv"; 
			try {
			    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".\\out\\"+FileName), "utf-8")); //TODO writing into subdir
			    writer.write(Header + "\n");
			} catch (IOException ex) {
				System.out.println("Cannot create/write into " + FileName);
			}
		}
		
		for (i=0; i<Countries.length; i++) {		
			OutpuLine=Countries[i][0] + Delimiter + Enclosure + Countries[i][ColumnIndex] + Enclosure;
			
			if (OutputType == "stdout") System.out.println(OutpuLine);
			
			if (OutputType == "file") {	
				try {
				    writer.write(OutpuLine + "\n");
				} catch (IOException ex) {
					System.out.println("Cannot write into " + FileName);
				}
			}
		}
		if (OutputType == "file") {	
			try {writer.close();} catch (Exception ex) {System.out.println("Cannot close " + FileName);}
		}	
	}
}
