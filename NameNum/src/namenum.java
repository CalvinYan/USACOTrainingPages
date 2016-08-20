/*
 ID: calviny1
 LANG: JAVA
 TASK: namenum
 */

import java.io.*;
import java.util.*;

public class namenum {

	private static ArrayList<String> dictionary = new ArrayList<String>();
	
	private static void update(String[] letters, int index, int strlen) {
		int length = dictionary.size();
		for (int i = 0; i < length; i++) {
			if (dictionary.get(i).length() != strlen) {
				dictionary.remove(i);
				length--;
				i--;
				continue;
			}
			boolean flag = false;
			for (int j = 0; j < 3; j++) {
				if (Character.toString(dictionary.get(i).charAt(index)).equals(letters[j])) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				dictionary.remove(i);
				length--;
				i--;
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner dictReader = new Scanner(new File("dict.txt"));
		while (dictReader.hasNext()) {
			dictionary.add(dictReader.next());
		}
		dictReader.close();
		Scanner in = new Scanner(new File("namenum.in"));
		PrintWriter out = new PrintWriter(new File("namenum.out"));
		String[][] keys = {{"A", "B", "C"}, {"D", "E", "F"}, {"G", "H", "I"}, {"J", "K", "L"}, {"M", "N", "O"}, {"P", "R", "S"}, {"T", "U", "V"}, {"W", "X", "Y"}};
		String s = in.nextLine();
		in.close();
		for (int i = 0; i < s.length(); i++) {
			int ind = Integer.parseInt(s.substring(i, i+1));
			update(keys[ind - 2], i, s.length());
		}
		for (int j = 0; j < dictionary.size(); j++) {
			out.println(dictionary.get(j));
		}
		if (dictionary.isEmpty()) out.println("NONE");
		out.close();
		System.exit(0);
	}

}
