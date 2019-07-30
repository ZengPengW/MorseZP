import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * ���� Morse˼��ļ����㷨
 * 
 * @author zp
 *
 */
public class MorseZP {
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(morseZpEncode("�й���"));
		//*..---J---..J=*--...K..---M=*---..i...--J=*..---a--...e=*.----j-----x.----X=*-....V--...Y=*..---a---..h=*--...x-----g=*--...S-----k=
		System.out.println(morseZpDecode("*..---J---..J=*--...K..---M=*---..i...--J=*..---a--...e=*.----j-----x.----X=*-....V--...Y=*..---a---..h=*--...x-----g=*--...S-----k="));
	}

	private static Map<Integer, String> enmap;
	private static Map<String, String> demap;

	static {
		enmap = new HashMap<Integer, String>();
		enmap.put(1, ".----");
		enmap.put(2, "..---");
		enmap.put(3, "...--");
		enmap.put(4, "....-");
		enmap.put(5, ".....");
		enmap.put(6, "-....");
		enmap.put(7, "--...");
		enmap.put(8, "---..");
		enmap.put(9, "----.");
		enmap.put(0, "-----");

		demap = new HashMap<String, String>();
		demap.put(".----", "1");
		demap.put("..---", "2");
		demap.put("...--", "3");
		demap.put("....-", "4");
		demap.put(".....", "5");
		demap.put("-....", "6");
		demap.put("--...", "7");
		demap.put("---..", "8");
		demap.put("----.", "9");
		demap.put("-----", "0");

	}

	/**
	 * 
	 * @param txt������һ��utf-8������ַ���
	 * @return ����һ�����ܺ���ַ���
	 * @throws UnsupportedEncodingException
	 */
	public static String morseZpEncode(String txt) throws UnsupportedEncodingException {
		byte[] bytes = txt.getBytes("utf-8");
		StringBuffer stringBuffer = new StringBuffer("");
		String s = null;
		char c;
		for (int i = 0; i < bytes.length; i++) {
			s = bytes[i] + "";
			for (int j = 0; j < s.length(); j++) {
				c = s.charAt(j);

				if (c == '-') {
					stringBuffer.append("*");
				} else {
					char letter;
					while (true) {
						letter = (char) (122 - (int) (Math.random() * 57));// �������A-z
						if (letter < 91 || letter > 96) {
							break;
						}
					}

					stringBuffer.append(enmap.get(c - '0'));
					stringBuffer.append(letter);
				}
			}
			stringBuffer.append("=");
		}

		return stringBuffer.toString();

	}

	/**
	 * 
	 * @param txt ����һ�����ܺ���ı���
	 * @return ����һ�����ܺ� ����Ϊutf-8�Ľ��
	 * @throws UnsupportedEncodingException
	 */
	public static String morseZpDecode(String txt) throws UnsupportedEncodingException {
		// String []text=Pattern.compile("[a-zA-Z]").split(txt);
		String[] text = txt.split("=");
		byte[] bs = new byte[text.length];
		int id = 0;// bs ��������
		String[] tex;
		for (int i = 0; i < text.length; i++) {
			if (text[i] == null || text[i].trim().length() == 0)
				continue;
			tex = Pattern.compile("[a-zA-Z]").split(text[i]);
			String te = "";
			if (tex[0].charAt(0) == '*') {
				te = "-";
				tex[0] = tex[0].substring(1);
			}

			for (int j = 0; j < tex.length; j++) {
				if (tex[j] == null || tex[j].trim().length() == 0)
					continue;
				te += demap.get(tex[j]);
			}
			int v = Integer.valueOf(te);
			bs[id++] = (byte) v;

		}

		return new String(bs, "utf-8");

	}

}
