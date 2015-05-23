package algos;

public class RemoveDups {

	/**
	 * @param args
	 */
	public static String removeDups(char[] s) {
		if (s == null)
			return null;
		if (s.length == 1)
			return s.toString();
		int tail = 0;
		for (int i = 0; i < s.length; i++) {
			boolean isDup = false;
			for (int j = 0; j < s.length; j++) {
				if (i == j)
					continue;
				if (s[i] == s[j]) {
					isDup = true;
					break;
				}
			}
			if (!isDup) {
				s[tail] = s[i];
				tail++;
			}
		}
		return new String(s, 0, tail);
	}

	public static String removeDuplicates(char[] str) {
		if (str == null)
			return null;
		int len = str.length;
		if (len < 2)
			return str.toString();

		int tail = 1;

		for (int i = 1; i < len; ++i) {
			int j;
			for (j = 0; j < tail; ++j) {
				if (str[i] == str[j])
					break;
			}
			if (j == tail) {
				str[tail] = str[i];
				++tail;
			}
		}
		str[tail] = 0;
		return new String(str);
	}

	public static void main(String[] args) {
		System.out.println(removeDuplicates("Hello".toCharArray()));
	}

}
