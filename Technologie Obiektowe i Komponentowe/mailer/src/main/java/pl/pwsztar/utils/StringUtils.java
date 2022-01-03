package pl.pwsztar.utils;

public class StringUtils {
  public static String addSpacesToLength(String text, int length) {
    StringBuilder textBuilder = new StringBuilder(text);
    for(int i = textBuilder.length(); i < length; i++) {
      textBuilder.append(" ");
    }
    return textBuilder.toString();
  }
}
