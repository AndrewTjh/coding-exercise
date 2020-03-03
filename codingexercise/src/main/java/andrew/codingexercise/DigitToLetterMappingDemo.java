package andrew.codingexercise;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 1,2,3,4,5
 * C(5,2)={12,13,14,15,23,24,25,34,35,45}
 * Cartesian product: X.Y
 * N-Dimensional Cartesian product: X1.X2.X3...XN
 * the iteration of production, as can be interpreted, 
 * original input, 
 * the first result as the second input,
 * the second result as the third input,
 * ...
 * */
public class DigitToLetterMappingDemo {
	public static void main(String[] args) {
		DigitToLetterMapper mapper = new DigitToLetterMapper("mapping-config.properties");
		System.out.println(mapper.convert(new Integer[]{0,1,2,3}));
	}
}

class DigitToLetterMapper {
	private Map<Integer,String> mapper = new HashMap<Integer,String>();
	{
		mapper.put(0, "");
		mapper.put(1, "");
		mapper.put(2, "A,B,C");
		mapper.put(3, "D,E,F");
		mapper.put(4, "G,H,I");
		mapper.put(5, "J,K,L");
		mapper.put(6, "M,N,O");
		mapper.put(7, "P,Q,R,S");
		mapper.put(8, "T,U,V");
		mapper.put(9, "W,X,Y,Z");
	}
	
	public DigitToLetterMapper() {}
	
	public DigitToLetterMapper(Map<Integer,String> mappingSet) {
		mapper.clear();
		Set<Map.Entry<Integer,String>> entries = mappingSet.entrySet();
		for (Map.Entry<Integer,String> entry : entries) {
			mapper.put(entry.getKey(), entry.getValue());
		}
	}
	
	public DigitToLetterMapper(Properties props) {
		mapper.clear();
		Set<Map.Entry<Object,Object>> entries = props.entrySet();
		for (Map.Entry<Object, Object> entry : entries) {
			mapper.put((Integer)entry.getKey(), (String)entry.getValue());
		}
	}
	
	// in classpath
	public DigitToLetterMapper(String filename) {
		mapper.clear();
		InputStream inStream = DigitToLetterMapper.class.getClassLoader().getResourceAsStream(filename);
		Properties props = new Properties();
		try {
			props.load(inStream);
			Set<Map.Entry<Object,Object>> entries = props.entrySet();
			for (Map.Entry<Object, Object> entry : entries) {
				mapper.put(Integer.parseInt((String)entry.getKey()), (String)entry.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// e.g. 2,3,4 or 0,2
	public String convert(Integer[] arr) {
		String result = "";
		for (int i = 0, n = arr.length; i < n; i++) {
			Integer key = arr[i];
			String val = mapper.get(key);
			result = doIteration(result, val);
		}
		return result;
	}
	
	// core algorithm
	// remember to deal with null string (null) or empty string ("") for robust purposes
	// the string should be replaced by string buffer when the content of the string is becoming too large
	private String doIteration(String prevResult, String currInput) {
		String result = "";
		String[] prevStrs = prevResult.split(",");
		String[] currStrs = currInput.split(",");
		String tempStr;
		for (int i = 0, n = prevStrs.length; i < n; i++) {
			for (int j = 0, m = currStrs.length; j < m; j++) {
				tempStr = prevStrs[i] + currStrs[j] + ",";
				result += tempStr;
			}
		}
		return result;
	}
}

