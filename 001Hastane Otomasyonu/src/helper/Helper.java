package helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	public static void opcButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "iptal");
		UIManager.put("OptionPane.noButtonText", "Hayir");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		
	}
	
	public static void SHOWmSG(String str) {
		String msg;
		opcButtonText();
		switch(str) {
		case "fill":
			msg = "Lusfen bilgileri giriniz";
			break;
		case "success":
			msg = "Islem Basarili";
			break;
		case "error":
			msg = "Bir hata gerceklesti";
			
			break;
			
			default: 
				msg = str;
				
		}
			
		JOptionPane.showMessageDialog(null, msg,"Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean confirm(String str) {
		String msg;
		opcButtonText();
		switch (str) {
		case "sure":
			msg = "Bu Islemi Gerceklestirmek istiyotmusun..";
			break;
		default:
			msg =str;
			break;
			
		}
		int result = JOptionPane.showConfirmDialog(null, msg,"DIKKAT  : ", JOptionPane.YES_NO_OPTION);
		if(result == 0) {
			return true;
		}else {
			return false;
		}
		
	}
	
}
