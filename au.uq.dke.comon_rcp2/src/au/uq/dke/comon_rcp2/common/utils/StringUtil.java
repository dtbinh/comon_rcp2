package au.uq.dke.comon_rcp2.common.utils;

public class StringUtil {

	public static String splitTextIntoLines(String text, int maxLines,
			int maxCharsPerLine) {
		text = text.replace('_', ' ').trim();
		StringBuffer buffer = new StringBuffer(text.length() + 10);
		if (text.length() > maxCharsPerLine) {
			int lines = 0;
			while ((text.length() > 0) && (lines < maxLines)) {
				// base case #1 - text is short
				if (text.length() < maxCharsPerLine) {
					buffer.append(text);
					break;
				}
				// base case #2 - added max lines
				if ((lines + 1) == maxLines) {
					// elide the remaining text (s) instead of just the current
					// line
					buffer.append(elideText(text, maxCharsPerLine));
					break;
				}

				// find a space and break on it
				int end = findWhiteSpace(text, maxCharsPerLine);
				if (end == -1) {
					end = Math.min(text.length(), maxCharsPerLine);
				}
				String line = text.substring(0, end).trim();
				if (line.length() == 0) {
					break;
				}

				buffer.append(line);
				buffer.append('\n');
				lines++;
				text = text.substring(end).trim();
			}
			return buffer.toString().trim();
		}
		return text;
	}
	
	public static String elideText(String text, int maxCharsPerLine) {
		if (text.length() > maxCharsPerLine) {
			return new String(text.substring(0, maxCharsPerLine).trim() + "...");
		}
		return text;
	}
	
	public static int findWhiteSpace(String s, int end) {
		int ws = -1;
		// look 2 characters past the end for a space character
		// and work backwards
		for (int i = Math.min(s.length() - 1, end + 2); i >= 0; i--) {
			if (Character.isWhitespace(s.charAt(i))) {
				ws = i;
				break;
			}
		}
		return ws;
	}



}
