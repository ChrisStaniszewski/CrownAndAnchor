package crownandanchor;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Testing the AllIn class methods")
class AllInTest {


	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@DisplayName("Calling constructor with an amount less then minimal amount throws an Exception.")
	@ParameterizedTest(name="Bet value of [{0}] throws the IllegalArgumentException")
	@ValueSource(ints= {2})

 final void testOrdinary1(int val) {

		assertThrows(IllegalArgumentException.class,() -> new AllIn("Crown",val));
	}

	
	@DisplayName("Testing the workOutWinnings method of the DoubleOrNothing class for every face name")
	@ParameterizedTest(name="Testing for face name index {0}")
	@ValueSource(ints= {0,1,2,3,4,5})

 final void testWorkOutWinnings(int index) {
        AllIn oBet=new AllIn(CADice.SYMBOLS[index],3);
        int val=index+1, nextVal=(val)%6+1;
        
        CADice[] dices0= {new CADice(nextVal),new CADice(nextVal),new CADice(nextVal)};
        CADice[] dices1= {new CADice(val),new CADice(nextVal),new CADice(nextVal)};
        CADice[] dices2= {new CADice(val),new CADice(val),new CADice(nextVal)};
        CADice[] dices3= {new CADice(val),new CADice(val),new CADice(val)};

        assertEquals(0,oBet.workOutWinnings(dices0));
        assertEquals(0,oBet.workOutWinnings(dices1));
        assertEquals(0,oBet.workOutWinnings(dices2));
        assertEquals(30,oBet.workOutWinnings(dices3));
        
	}
	

	
	

}
