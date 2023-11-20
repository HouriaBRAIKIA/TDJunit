package JunitTD5;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

//follows convention MethodName_StateUnderTest_ExpectedBehavior

public class ABRTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private PrintStream stdOut = System.out ;
	private static ABR<Integer> ABRUnderTest;  
	
	@BeforeEach
	public void setUpTests() {
	    System.setOut(new PrintStream(outContent));
	    ABRUnderTest = new ABR<Integer>();
	}

	@AfterEach
	public void restoreAfterTests() {
	    System.setOut(stdOut);
	    ABRUnderTest = null;
	}
	
	@Test
	void inWalk_positiveIntegers_printsThemInAscendingOrder() {
		//Arrange
		int[] values = {5,2,17,20,12,14,13,9,7};
		
		for(int i = 0;i< values.length;i++) {
			ABRUnderTest.add(values[i]);
		}
		
		
		//Act
		ABRUnderTest.inWalk();
		
		//Assert
		assertEquals("2\t5\t7\t9\t12\t13\t14\t17\t20\t",outContent.toString());
	}
	
	@Test
	void max_positiveIntegers_returnsMaxValue() {
		//Arrange
		int[] values = {5,2,17,20,12,14,13,9,7};
		
		for(int i = 0;i< values.length;i++) {
			ABRUnderTest.add(values[i]);
		}
		
		
		//Act
		int res = (int) ABRUnderTest.max();
		
		//Assert
		assertEquals(res,20);
	}
	
	@ParameterizedTest()
	@CsvSource({ "5,true", "21,false", "13,true" })
	void contains_positiveIntegers_returnsIfValueIsContained(
	        int arg, boolean isContained) {
		//Arrange
		int[] values = {5,2,17,20,12,14,13,9,7};
		
		for(int i = 0;i< values.length;i++) {
			ABRUnderTest.add(values[i]);
		}
		
		
		//Act
		boolean res = ABRUnderTest.contains(arg);
		
		//Assert
		assertEquals(isContained,res);
	}
	
	@ParameterizedTest()
	@ValueSource(ints = { 1, 2, 5, 12, 103 })
	void add_positiveIntegers_AnAddedValueShouldBeContained(int arg0) {
		//Arrange
		int[] values = {5,2,17,20,12,14,13,9,7};
		
		for(int i = 0;i< values.length;i++) {
			ABRUnderTest.add(values[i]);
		}
		
		
		//Act
		ABRUnderTest.add(arg0);
		
		//Assert
		assertEquals(ABRUnderTest.contains(arg0),true);
	}

}
