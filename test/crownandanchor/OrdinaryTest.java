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

@DisplayName("Testing the Ordinary and the Bet class methods")
class OrdinaryTest {


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
	@ValueSource(ints= {0,-1})

 final void testOrdinary1(int val) {

		assertThrows(IllegalArgumentException.class,() -> new Ordinary("Crown",val));
	}

	
	@DisplayName("Testing the countMatches method of the Bet superclass for every face name")
	@ParameterizedTest(name="Testing for face name index {0}")
	@ValueSource(ints= {0,1,2,3,4,5})

 final void testCountMatches(int index) {
        Ordinary oBet=new Ordinary(CADice.SYMBOLS[index],1);
        int val=index+1, nextVal=(val)%6+1;
        
        CADice[] dices0= {new CADice(nextVal),new CADice(nextVal),new CADice(nextVal)};
        CADice[] dices1= {new CADice(val),new CADice(nextVal),new CADice(nextVal)};
        CADice[] dices2= {new CADice(val),new CADice(val),new CADice(nextVal)};
        CADice[] dices3= {new CADice(val),new CADice(val),new CADice(val)};

        assertEquals(0,oBet.countMatches(dices0));
        assertEquals(1,oBet.countMatches(dices1));
        assertEquals(2,oBet.countMatches(dices2));
        assertEquals(3,oBet.countMatches(dices3));
        
	}
	
	
	@DisplayName("Testing the workOutWinnings method of the Orinary superclass for every face name")
	@ParameterizedTest(name="Testing for face name index {0}")
	@ValueSource(ints= {0,1,2,3,4,5})

 final void testWorkOutWinnings(int index) {
        Ordinary oBet=new Ordinary(CADice.SYMBOLS[index],1);
        int val=index+1, nextVal=(val)%6+1;
        
        CADice[] dices0= {new CADice(nextVal),new CADice(nextVal),new CADice(nextVal)};
        CADice[] dices1= {new CADice(val),new CADice(nextVal),new CADice(nextVal)};
        CADice[] dices2= {new CADice(val),new CADice(val),new CADice(nextVal)};
        CADice[] dices3= {new CADice(val),new CADice(val),new CADice(val)};

        assertEquals(0,oBet.workOutWinnings(dices0));
        assertEquals(2,oBet.workOutWinnings(dices1));
        assertEquals(3,oBet.workOutWinnings(dices2));
        assertEquals(4,oBet.workOutWinnings(dices3));
        
	}
	

	
	

}
