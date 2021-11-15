package tienda.daniel.utils;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.jasypt.util.password.StrongPasswordEncryptor;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import tienda.daniel.models.Productos;
public class StringUtilities {
	

	
	public static Timestamp getDefaultTimestamp() {
		Date date = new Date();
		Timestamp def = new Timestamp(date.getTime());
		return def;
	}
	
	public static String getEncryptedPassword(String pass) {
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(pass);
		
		return encryptedPassword;
	}

	public static Timestamp getTimestampFromString(String fecha) {
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = new Date();
		try {
			date = format.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Timestamp fecha_bajaFi = new Timestamp(date.getTime());
		
		return fecha_bajaFi;
		
	}
	
	

		
	

}
