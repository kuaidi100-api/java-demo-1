

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Real-time Shipment Tracking
 *
 * @author https://www.kd100.com/
 *
 */
public class RealTimeTracking {

	private static final String URL = "https://www.kd100.com/api/v1/tracking/realtime";
	//You can find your ApiKey on https://app.kd100.com/api-managment
	private static final String API_KEY = "";
	//You can find your Secret on https://app.kd100.com/api-managment
	private static final String SECRET = "";
	private static final int CONNECT_TIMEOUT = 1000;
	private static final int READ_TIMEOUT = 5000;

	public static void main(String[] args) {
		String carrier_id = "dhl";
		String tracking_number = "9926933413";
		String phone = "95279527";
		String ship_from = "Toronto, Canada";
		String ship_to = "Los Angeles, CA, United States";

		RealTimeTracking demo = new RealTimeTracking();
		String result = demo.getData(carrier_id, tracking_number, phone, ship_from, ship_to);
		System.out.println(result);
	}
	


	public String getData(String carrier_id, String tracking_number, String phone, String ship_from, String ship_to) {

		String param = "{" +
				"\"carrier_id\": \""+carrier_id+"\"," +
				"\"tracking_number\": \""+tracking_number+"\"," +
				"\"phone\": \""+phone+"\"," +
				"\"ship_from\": \""+ship_from+"\"," +
				"\"ship_to\": \""+ship_to+"\"," +
				"\"area_show\": 1," +
				"\"order\": \"desc\"" +
				"}";
		//MD5  encryption
		String signature = MD5Utils.encode(param + API_KEY + SECRET);
		//SHA256  encryption
		//String signature = SHA256Utils.encode(param + API_KEY + SECRET);
		return this.post(param,signature);
	}
	

	public String post(String param,String signature) {
		StringBuffer response = new StringBuffer("");

		byte[] data = param.getBytes();

		BufferedReader reader = null;
		OutputStream out = null;
		try {

			URL url = new URL(URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(CONNECT_TIMEOUT);
			conn.setReadTimeout(READ_TIMEOUT);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Content-Length", String.valueOf(data.length));
			conn.setRequestProperty("API-Key",API_KEY);
			conn.setRequestProperty("signature",signature);
			conn.setDoOutput(true);

			out = conn.getOutputStream();
			out.write(data);

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			String line = "";
            while ((line = reader.readLine()) != null) {
            	response.append(line);
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out){
					out.flush();
					out.close();
				}
				if (null != reader) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return response.toString();
	}
}


class MD5Utils {
	private static MessageDigest mdigest = null;
	private static char digits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

	private static MessageDigest getMdInst() {
		if (null == mdigest) {
			try {
				mdigest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return mdigest;
	}

	public static String encode(String s) {
		if(null == s) {
			return "";
		}

		try {
			byte[] bytes = s.getBytes();
			getMdInst().update(bytes);
			byte[] md = getMdInst().digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = digits[byte0 >>> 4 & 0xf];
				str[k++] = digits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
class SHA256Utils{
	public static String encode(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeStr;
    }
    private static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) { 
                stringBuilder.append("0");
            }
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }
}

