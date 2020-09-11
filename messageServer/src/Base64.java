
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64
{
	public static String encode(byte[] buffer)
	{
		Encoder er=java.util.Base64.getEncoder();
		String res=er.encodeToString(buffer);
		return res;
	}
	public static byte[] decode(String str)
	{
		Decoder dr=java.util.Base64.getDecoder();
		return dr.decode(str);
	}
}
