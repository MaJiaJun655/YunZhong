//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package minecraft.yunzhong.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Profile {
	public Profile() {
	}

	public static final void ReloadProfile() {
		FileInputStream fis = null;
		Properties properties = new Properties();
		
		File folder = new File("plugins/YunZhong");
		File file = new File("plugins/YunZhong/YunZhong.properties");
		
		if (!folder.exists()) {
			folder.mkdir();
		}

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException var11) {
				var11.printStackTrace();
			}
		}

		try {
			fis = new FileInputStream(file);
			properties.load(fis);
		} catch (FileNotFoundException var10) {
			var10.printStackTrace();
		} catch (IOException var9) {
			var9.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		if (properties.getProperty("BossName") == null) {
			properties.setProperty("BossName", "Jetren");
		}

		if (properties.getProperty("F") == null) {
			properties.setProperty("F", "/help");
			properties.setProperty("F_state", "true");
		}

		if (properties.getProperty("Anti_cheating_fall_state") == null) {
			properties.setProperty("Anti_cheating_fall_state", "true");
		}

		if (properties.getProperty("public_off_fly") == null) {
			properties.setProperty("public_off_fly", "true");
		}

		if (properties.getProperty("sleep_morning") == null) {
			properties.setProperty("sleep_morning", "true");
		}


	}


}
