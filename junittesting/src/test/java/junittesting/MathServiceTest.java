/**
 * 
 */
package junittesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * @author jmusham
 *
 */
class MathServiceTest {
	
	/**
	 * Defining of MathService object
	 */
	MathService mathService = new MathService();

	/**
	 * Test method for {@link junittesting.MathService#addition(int, int)}.
	 */
	@Test
	void testAddition() {
		assertEquals(11, mathService.addition(5, 6));
	}

	/**
	 * Test method for {@link junittesting.MathService#subtraction(int, int)}.
	 */
	@Test
	void testSubtraction() {
		assertEquals(1, mathService.subtraction(6, 5));
	}

	/**
	 * Test method for {@link junittesting.MathService#multiplication(int, int)}.
	 */
	@Test
	void testMultiplication() {
		assertEquals(30, mathService.multiplication(5, 6));
	}

	/**
	 * Test method for {@link junittesting.MathService#multiplication(int, int)}.
	 */
	@Test
	void testDivision() {
		assertEquals(2, mathService.division(6, 3));
	}

	/**
	 * Test method for {@link junittesting.MathService#division(int, int)}.
	 */
	@Test
	void testDivisionWithException() {
		assertThrows(ArithmeticException.class, () -> mathService.division(6, 0), "Not throwing exception as it is not devided by zero");
		
		
		//assertArrayEquals(expected, actual);
		//assertIterableEquals(expected, actual);
		//assert
		
	}

}
