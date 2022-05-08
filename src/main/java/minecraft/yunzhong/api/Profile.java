package minecraft.yunzhong.api;

import org.bukkit.Bukkit;

import java.io.*;
import java.util.Properties;

public class Profile {
	public final static void ReloadProfile() {
		FileInputStream fis = null;
		  Properties properties = new Properties();
		  File file = new File("plugins/YunZhong/YunZhong.properties");
		  File folder = new File("plugins/YunZhong");
		  if(!folder.exists()){
			  folder.mkdir();
		  }
		  if(!file.exists()){//????????????
			  try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//???????
		  }
		   try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			properties.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  if(properties.getProperty("BossName")==null) {
			  properties.setProperty("BossName","Jetren");
		  }		
		  if(properties.getProperty("F")==null) {
			  properties.setProperty("F","/help");
			  properties.setProperty("F_state","true");
		  }	
		  if(properties.getProperty("Anti_cheating_fall_state")==null) {
			  properties.setProperty("Anti_cheating_fall_state","true");
		  }	
		  if(properties.getProperty("public_off_fly")==null) {
			  properties.setProperty("public_off_fly","true");
		  }	
		  if(properties.getProperty("sleep_morning")==null) {
			  properties.setProperty("sleep_morning","true");
		  }
		  FileWriter fWriter = null;
		try {
			fWriter = new FileWriter("plugins/yzzm/yzzm.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			properties.store(fWriter, "yzzm");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static  final FileInputStream initialization() {
		  FileInputStream fis = null;
		  Properties properties = new Properties();
		  File file = new File("plugins/yzzm/yzzm.properties");
		  try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return fis;
	}
}
