package junittesting;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * @author jmusham
 *
 */
//test instance to create per class default is create test instance per each test method, we can verify this by constructor method
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathUtilsTest {
	
//	MathService mathService = Mockito.mock(MathService.class);
	static MathService mathService;

	// Static keyword has to be added for before all and after all methods
	/**
	 * Initialization method
	 */
	@BeforeAll
	public static void init() {
		System.out.println("This is a before all execution");
		mathService = new MathService();
	}

	/**
	 * The constructor
	 */
	public MathUtilsTest() {
		System.out.println("This is constructor");
	}

	MathUtils mathUtils;


	/**
	 * Setup method
	 */
	@BeforeEach
	public void setup() {
		mathUtils = new MathUtils(mathService);
		System.out.println("This is a before each test execution");
	}

	/**
	 * Test the addition
	 */
	@Test
	@DisplayName("TestingAdditionMethod") // This is just to give display name which is useful to recognize the test
											// case, we also can use the display name for class identification as well
	public void testAddition() {
		System.out.println("The result is:");

		// Assert (11 == mathUtils.addition(5, 6));
		assertEquals(16, mathUtils.performOperation(5, 6, Operation.SUM));
	}

	/**
	 * Test the multiplication
	 */
	@RepeatedTest(3)
	public void testMultiplication(RepetitionInfo repetitionInfo) {
		if (repetitionInfo.getCurrentRepetition() == 1)
		{
			System.out.println("Total repetitions: " + repetitionInfo.getTotalRepetitions());
		}
		System.out.println("Current repetition: " + repetitionInfo.getCurrentRepetition());
		assertEquals(30, mathUtils.performOperation(5, 6, Operation.MUL));
	}

	/**
	 * Test the disable feature
	 */
	@Test
//	@Disabled
	public void disableTestMethods() {
		System.out.println("This is a disabled method");
	}

	/**
	 * Test the multiplication method with different options
	 */
	@Test
	@DisplayName("TestWithMultipleOptions")
	public void testMultiplicationWithMultipleOption() {
		assertAll(() -> assertEquals(30, mathUtils.performOperation(5, 6, Operation.MUL)),
				() -> assertEquals(35, mathUtils.performOperation(5, 7, Operation.MUL)),
				() -> assertEquals(40, mathUtils.performOperation(5, 8, Operation.MUL)));

	}

	/**
	 * The release method
	 */
	@AfterEach
	public void release() {
		System.out.println("This is a after each test execution\n\n");
	}

	/**
	 * The destroy method
	 */
	@AfterAll
	public static void destroy() {
		System.out.println("This is a after all execution");
	}

}
