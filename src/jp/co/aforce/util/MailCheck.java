package jp.co.aforce.util;

public class MailCheck {

	public boolean check(String mailaddress) {

		String at = "@";
		boolean isMailAddress = false;
		char[] c = at.toCharArray();
		char c1 = 0;
		for(char c2: c) {
			c1 = c2;
		}

		if(!mailaddress.startsWith(at) && !mailaddress.endsWith(at) && mailaddress.contains(at) && countStr(mailaddress, c1 ) == 1) {
			isMailAddress = true;
		}

		return isMailAddress;
	}

	protected int countStr(String str, char target) {
		int count = 0;

		for(char i : str.toCharArray()) {
			if(i == target) {
				count++;
			}
		}
		return count;
	}

}
