package pl.pwsztar.worldtimeapp.utils;

public class StringUtils {
  public static String join(Object... values) {
    StringBuilder sb = new StringBuilder("");
    for (int i = 0; i < values.length; i++) {
      sb.append(values[i]);
    }
    return sb.toString();
  }

  public static String fillZerosOnStart( int value, int range ) {
    String string = String.valueOf(value);
    if( string.length() == range ) return string;
    while( string.length() != range ) {
      string = StringUtils.join("0", string);
    }
    return string;
  }
}
