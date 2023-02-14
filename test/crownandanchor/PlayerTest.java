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

@DisplayName("Testing the Player class methods")
class PlayerTest {
	private Player tPlayer;
	private static int stake;
	private static String name;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		name="Test";
		stake=15;
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		tPlayer=new Player(name,stake);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@DisplayName("Calling constructor with an empty or blank name throws an Exception")
	@ParameterizedTest(name="Name [{0}] throws the IllegalArgumentException")
	@ValueSource(strings= {"","  ","\t\n"})
	final void testPlayer(String tName) {
		assertThrows(IllegalArgumentException.class,() -> new Player(tName,10));
	}
	
	@DisplayName("Calling constructor with a negative stake throws an Exception")
	@ParameterizedTest(name="Stake value [{0}] throws the IllegalArgumentException")
	@ValueSource(ints= {-1})

	final void testPlayer1(int tStake) {

		assertThrows(IllegalArgumentException.class,() -> new Player("Test",tStake));
	}

	
	
	

	@DisplayName("Calling incStake with a negative amount throws an Exception and leave the stake unchanged")
	@ParameterizedTest(name="Amount value of [{0}] throws the IllegalArgumentException")
	@ValueSource(ints= {-1})

	final void testIncStake(int val) {
		assertAll("incStake",
				()->assertThrows(IllegalArgumentException.class,() -> tPlayer.incStake(val)),
				()->assertEquals(stake,tPlayer.getStake()));
	}

	@DisplayName("Calling decStake with a negative or exceeding stake amount throws an Exception and leave the stake unchanged")
	@ParameterizedTest(name="Amount value of [{0}] throws the IllegalArgumentException")
	@ValueSource(ints= {-1, 16})
	final void testDecStake(int val) {
		assertAll("decStake",
				()->assertThrows(IllegalArgumentException.class,() -> tPlayer.decStake(val)),
				()->assertEquals(stake,tPlayer.getStake()));
	
	}


	
	@DisplayName("Calling incBanker with a negative amount throws an Exception and leave banker unchanged")
	@ParameterizedTest(name="Amount value of [{0}] throws the IllegalArgumentException")
	@ValueSource(ints= {-1})

	final void testIncBanker(int val) {
		int oldBanker=tPlayer.getBanker();
		assertAll("incBanker",
				()->assertThrows(IllegalArgumentException.class,() -> tPlayer.incBanker(val)),
				()->assertEquals(oldBanker,tPlayer.getBanker()));
		
	}


	@DisplayName("Calling incBanker with a non negative int increases banker by this amount")
	@ParameterizedTest(name="Amount value of [{0}] increases stake")
	@ValueSource(ints= {0,10})

	final void testIncBanker1(int val) {
		int oldBanker=tPlayer.getBanker();
		tPlayer.incBanker(val);
		assertAll("incBanker",
				()->assertEquals(oldBanker+val,tPlayer.getBanker()));
		
	}


	
	
	
	@DisplayName("Calling decBanker with a negative or exceeding banker amount throws an Exception and leave the banker unchanged")
	@ParameterizedTest(name="Amount value of [{0}] throws the IllegalArgumentException")
	@ValueSource(ints= {-1,101})
	final void testDecBanker(int val) {
		int oldBanker=tPlayer.getBanker();
		assertAll("decBanker",
				()->assertThrows(IllegalArgumentException.class,() -> tPlayer.decBanker(val)),
				()->assertEquals(oldBanker,tPlayer.getBanker()));

		
		
	}

	@DisplayName("Calling decBanker with a non negative value decreases banker by this value")
	@ParameterizedTest(name="Amount value of [{0}] increases banker by {0}")
	@ValueSource(ints= {0,1,100})
	final void testDecBanker1(int val) {
		int oldBanker=tPlayer.getBanker();
		tPlayer.decBanker(val);
		assertAll("decBanker",
				()->assertEquals(oldBanker-val,tPlayer.getBanker()));

	}
}
