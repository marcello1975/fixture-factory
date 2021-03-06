package br.com.six2six.fixturefactory.function;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

import org.junit.Test;

import br.com.six2six.bfgex.Gender;
import br.com.six2six.fixturefactory.base.Range;

public class RandomFunctionTest {

    @Test
    public void randomByteTest() {
        Object value = new RandomFunction(Byte.class).generateValue();
        assertNotNull("Generated byte must not be null", value);
        assertTrue("Generated value is not a byte", value instanceof Byte);
    }
    
    @Test
    public void randomShortTest() {
        Object value = new RandomFunction(Short.class).generateValue();
        assertNotNull("Generated short must  not be null", value);
        assertTrue("Generated value is not a short", value instanceof Short);
    }

	@Test
	public void randomIntegerTest() {
		Object value = new RandomFunction(Integer.class).generateValue();
		assertNotNull("Generated integer can not be null", value);
		assertTrue("Generated value is not a Integer", value instanceof Integer);
	}

    @Test
    public void randomLongTest() {
        Object value = new RandomFunction(Long.class).generateValue();
        assertNotNull("Generated long can not be null", value);
        assertTrue("Generated value is not a Long", value instanceof Long);
    }
    
	@Test
	public void randomFloatTest() {
		Object value = new RandomFunction(Float.class).generateValue();
		assertNotNull("Generated float can not be null", value);
		assertTrue("Generated value is not a Float", value instanceof Float);
	}

	@Test
	public void randomDoubleTest() {
		Object value = new RandomFunction(Double.class).generateValue();
		assertNotNull("Generated double can not be null", value);
		assertTrue("Generated value is not a Double", value instanceof Double);
	}

	@Test
	public void randomBigDecimalTest() {
	    Object value = new RandomFunction(BigDecimal.class).generateValue();
	    assertNotNull("Generated BigDecimal must not be null", value);
	    assertTrue("Generated value is not a BigDecimal", value instanceof BigDecimal);
	}
	
	@Test
	public void randomBigDecimalWithMathContextTest() {
	    Object value = new RandomFunction(BigDecimal.class, new MathContext(3, RoundingMode.HALF_EVEN)).generateValue();
	    assertNotNull("Generated BigDecimal must not be null", value);
	    assertTrue("Generated value is not a BigDecimal", value instanceof BigDecimal);
	    assertTrue("Generated value should have a precision of 3", ((BigDecimal) value).precision() == 3);
	}

	@Test
	public void randomBigIntegerTest() {
	    Object value = new RandomFunction(BigInteger.class).generateValue();
	    assertNotNull("Generated BigInteger must not be null", value);
	    assertTrue("Generated value is not a BigInteger", value instanceof BigInteger);
	}

	@Test
	public void randomBooleanTest() {
		Object value = new RandomFunction(Boolean.class).generateValue();
		assertNotNull("Generated boolean can not be null", value);
		assertTrue("Generated value is not a Boolean", value instanceof Boolean);
	}
	
	@Test
	public void randomDatasetTest() {
		String[] names = {"Anderson", "Arthur", "Douglas"};
		Object value = new RandomFunction(names).generateValue();
		assertNotNull("Generated name can not be null", value);
		assertTrue("Generated name does not exist in the dataset names", Arrays.asList(names).contains(value));
	}

	@Test
	public void randomByteRangeTest() {
	    Byte start = 1, end = 100;
	    Byte value = new RandomFunction(Byte.class, new Range(start, end)).generateValue();
	    assertNotNull("Generated byte must not be null", value);
	    assertTrue("Generated byte does not exist in the range", (start <= value && value <= end));
	}

	@Test
	public void randomShortRangeTest() {
	    Short start = 1, end = 100;
	    Short value = new RandomFunction(Short.class, new Range(start, end)).generateValue();
	    assertNotNull("Generated short must not be null", value);
	    assertTrue("Generated short does not exist in the range", (start <= value && value <= end));
	}

	@Test
	public void randomLongRangeTest() {
		Long start = 85L, end = 95L;
		Object value = new RandomFunction(Long.class, new Range(start, end)).generateValue();
		assertNotNull("Generated long can not be null", value);
		assertTrue("Generated long does not exist in the range", (start <= (Long) value && (Long) value <= end));
	}

	@Test
	public void randomBigDecimalRangeChoosesStartScaleTest() {
	    BigDecimal start = new BigDecimal("2.313"), end = new BigDecimal("3.73");
	    BigDecimal value = new RandomFunction(BigDecimal.class, new Range(start, end)).generateValue();
	    assertNotNull("Generated BigDecimal can not be null", value);
	    assertTrue("Generated BigDecimal does not exist in the range", (start.compareTo(value) <= 0 && value.compareTo(end) <= 0));
	    assertTrue("Generated BigDecimal should most precise scale", value.scale() == start.scale());
	}

	@Test
	public void randomBigDecimalRangeChoosesEndScaleTest() {
	    BigDecimal start = new BigDecimal("2.31"), end = new BigDecimal("3.731");
	    BigDecimal value = new RandomFunction(BigDecimal.class, new Range(start, end)).generateValue();
	    assertNotNull("Generated BigDecimal can not be null", value);
	    assertTrue("Generated BigDecimal does not exist in the range", (start.compareTo(value) <= 0 && value.compareTo(end) <= 0));
	    assertTrue("Generated BigDecimal should most precise scale", value.scale() == end.scale());
	}

	@Test
	public void randomBigIntegerRangeTest() {
	    BigInteger start = new BigInteger("2147483648"), end = new BigInteger("2147483650");
	    BigInteger value = new RandomFunction(BigInteger.class, new Range(start, end)).generateValue();
	    assertNotNull("Generated BigInteger can not be null", value);
	    assertTrue("Generated BigInteger does not exist in the range", (start.compareTo(value) <= 0 && value.compareTo(end) <= 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void randomLongRangeIncorrectTest() {
		Long start = 80L, end = 80L;
		new RandomFunction(Long.class, new Range(start, end)).generateValue();
	}

	@Test(expected = IllegalArgumentException.class)
	public void randomDoubleRangeIncorrectTest() {
		Double start = 80.0, end = 80.0;
		new RandomFunction(Long.class, new Range(start, end)).generateValue();
	}
	
	@Test
	public void randomFunction() {
		Function[] functions = {new NameFunction(), new NameFunction(Gender.MALE), new NameFunction(Gender.FEMALE)};
		Object value = new RandomFunction(functions).generateValue();
		assertNotNull("Generated value can not be null", value);
	}
	
	@Test
	public void randomEnum() {
		Object value = new RandomFunction(Gender.class).generateValue();
		assertNotNull("Generated enum can not be null", value);
		assertTrue("Generated value is not a Enum", value instanceof Enum);
	}
	
}
