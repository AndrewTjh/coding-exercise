package andrew.codingexercise;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class DigitToLetterMappingDemoTest {
 	// load from config file in classpath
	/*
	 * 0=A,B
	 * 1=C,D,E
	 * 2=F,G,H,I
	 * 3=J,K,L,M,N
	 * */
	@Ignore
	@Test
	public void testConvert1() {
		DigitToLetterMapper mapper = new DigitToLetterMapper("mapping-config.properties");
		assertEquals("AC,AD,AE,BC,BD,BE,", mapper.convert(new Integer[]{0,1}));
	}
	
	// test convert with default settings
	@Ignore
	@Test
	public void testConvert2() {
		DigitToLetterMapper mapper = new DigitToLetterMapper();
		System.out.println(mapper.convert(new Integer[]{3,2,1}));
	}
	
	// test convert with designated settings
	@Ignore
	@Test
	public void testConvert3() {
		Map<Integer,String> settings = new HashMap<Integer,String>();
		settings.put(1, "T,K");
		settings.put(2, "O,P,R");
		settings.put(3, "H,A,L");
		DigitToLetterMapper mapper = new DigitToLetterMapper(settings);
		System.out.println(mapper.convert(new Integer[]{3,2,1}));
	}
}
