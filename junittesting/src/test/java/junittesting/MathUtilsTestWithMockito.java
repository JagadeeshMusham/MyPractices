package junittesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

/**
 * @author jmusham
 *
 */
//test instance to create per class default is create test instance per each test method, we can verify this by constructor method
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathUtilsTestWithMockito {

	/**
	 * The constructor
	 */
	public MathUtilsTestWithMockito() {
		System.out.println("This is constructor");
	}

	/**
	 * The MathUtils object
	 */
	MathUtils mathUtils;

	/**
	 * The MathService object
	 */
	static MathService mathService; // one way to mock

	// Static keyword has to be added for before all and after all methods
	/**
	 * Initialization method
	 */
	@BeforeAll
	public static void init() {
		System.out.println("This is a before all execution");
		mathService = Mockito.mock(MathService.class);
	}

	/**
	 * Setup method
	 */
	@BeforeEach
	public void setup() {
		mathUtils = new MathUtils(mathService);
	}

	/**
	 * Demonstrating the Mockito method
	 */
	@Test
	@DisplayName("TestingAdditionMethod") // This is just to give display name which is useful to recognize the test
											// case, we also can use the display name for class identification as well
	public void testAddition() {
		System.out.println("The TestingAdditionMethod method:");

		when(mathService.addition(5, 6)).thenReturn(30);
		assertEquals(35, mathUtils.performOperation(5, 6, Operation.SUM));
		verify(mathService).addition(5, 6); // this is to verify the define mock method usage
		System.out.println("======\n\n");
	}

	@Test
	public void testStaticMethods() {
		System.out.println("The testStaticMethods method:");
		when(mathService.addition(5, 7)).thenReturn(30);
		try (MockedStatic<MathService> service = Mockito.mockStatic(MathService.class)) {
			service.when(MathService::display).thenReturn("Eugen");
			assertEquals(35, mathUtils.performOperation(5, 7, Operation.SUM));
		}

		System.out.println("======\n\n");

	}

	@Test
	public void testStaticVoidMethods() {
		System.out.println("The testStaticVoidMethods method:");
		when(mathService.addition(5, 8)).thenReturn(30);

		try (MockedStatic<MathService> service = Mockito.mockStatic(MathService.class)) {
			// mocking static method which returns String
			service.when(MathService::display).thenReturn("display");
			
			// mocking static method with arguments which returns String
			service.when(() -> MathService.displayWithArgument(3)).thenReturn("display arguments");
			
			// mocking static method which returns void
			service.when(() -> MathService.displayVoid()).then(invockonMock -> null); 
																						
			assertEquals(35, mathUtils.performOperation(5, 8, Operation.SUM));
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("======\n\n");

	}

//	/**
//	 * Test multiplication method
//	 */
//	@Test
//	public void testMultiplication() {
//		assertEquals(30, mathUtils.performOperation(5, 6, Operation.MUL));
//	}

}
