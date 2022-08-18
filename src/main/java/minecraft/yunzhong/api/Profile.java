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
		File file = new File("plugins/YunZhong/YunZhong.properties");
		File folder = new File("plugins/YunZhong");
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
		} catch (FileNotFoundException var10) {
			var10.printStackTrace();
		}

		try {
			properties.load(fis);
		} catch (IOException var9) {
			var9.printStackTrace();
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

		FileWriter fWriter = null;

		try {
			fWriter = new FileWriter("plugins/yzzm/yzzm.properties");
		} catch (IOException var8) {
			var8.printStackTrace();
		}

		try {
			properties.store(fWriter, "yzzm");
		} catch (IOException var7) {
			var7.printStackTrace();
		}

		try {
			fis.close();
		} catch (IOException var6) {
			var6.printStackTrace();
		}

	}

	public static final FileInputStream initialization() {
		FileInputStream fis = null;
		new Properties();
		File file = new File("plugins/yzzm/yzzm.properties");

		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException var4) {
			var4.printStackTrace();
		}

		return fis;
	}
}
