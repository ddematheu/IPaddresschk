import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class prob {

	// TODO Auto-generated method stub
	static Pattern pattern;
	static Matcher matcher;
	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public prob() {
		pattern = Pattern.compile(IPADDRESS_PATTERN);
	}

	public static int validate(final String username) {

		matcher = pattern.matcher(username);
		if(matcher.matches()== true)
			return 0;
		else
			return 1;

	}

	public static long ipToLong(InetAddress ip) {
		byte[] octets = ip.getAddress();
		long result = 0;
		for (byte octet : octets) {
			result <<= 8;
			result |= octet & 0xff;
		}
		return result;
	}

	public static boolean isValidRange(String ipStart, String ipEnd,
			String ipToCheck) {
		try {
			long ipLo = ipToLong(InetAddress.getByName(ipStart));
			long ipHi = ipToLong(InetAddress.getByName(ipEnd));
			long ipToTest = ipToLong(InetAddress.getByName(ipToCheck));
			return (ipToTest >= ipLo && ipToTest <= ipHi);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) throws IOException {

		new prob();
		String input = null;
		
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        
	        while (line != null) {
	        	String range[] = line.split(" ");
	    		if(validate(range[2])==1)
	    			System.out.println("InValid");
	    		else{
	    			if(isValidRange(range[0], range[1], range[2])==true)
	    				System.out.println("InRange");
	    			else
	    				System.out.println("OutRange");
	    		}
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
        
	    } finally {
	        br.close();
	    }
		
	}

}
