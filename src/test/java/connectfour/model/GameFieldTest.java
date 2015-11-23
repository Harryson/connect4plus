package connectfour.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import connectfour.controller.Connect4GameController;
import connectfour.ui.tui.TUI;
import modelinterfaces.Player;

class MockGameField extends Connect4GameField {

	public MockGameField(Player player1, Player player2) {
		super(player1, player2);
	}
	
	public boolean dropCoin(int column, Player p) {
		playerOnTurn_$eq(p);
		final boolean success = dropCoin(column);
		playerOnTurn_$eq(p);
		return success;
	}
	
}

class MockController extends Connect4GameController {
	public MockController(String player1Name, String player2Name) {
		super(player1Name, player2Name);
		this.gameField_$eq(new MockGameField(player1(), player2()));
	}
}

public class GameFieldTest {
	MockGameField gameField;
	Player player;
	Player opponent;

	private Connect4GameController controller;

	@Before
	public void setUp() throws Exception {
		controller = new MockController("Hugo", "Boss");
		
		player = controller.player1();
		opponent = controller.player2();
		gameField = (MockGameField) controller.gameField();
	}

	@After
	public void tearDown() throws Exception {
		//gameField = new GameField(player, opponent);
	}

	@Test
	public void dropCointTest() {
		boolean success;

		success = gameField.dropCoin(3, player);
		assertTrue(success);
		assertEquals(controller.getPlayerAt(0, 3), player);
		
		success = gameField.dropCoin(3, player);
		assertEquals(controller.getPlayerAt(1, 3), player);
		assertTrue(success);
		
		success = gameField.dropCoin(1, opponent);
		assertEquals(controller.getPlayerAt(0, 1), opponent);
		assertTrue(success);
		
		success = gameField.dropCoin(1, opponent);
		assertEquals(controller.getPlayerAt(1, 1), opponent);
		assertTrue(success);
		
		success = gameField.dropCoin(1, opponent);
		assertTrue(success);
		assertEquals(controller.getPlayerAt(2, 1), opponent);
		
		success = gameField.dropCoin(1, opponent);
		assertTrue(success);
		assertEquals(controller.getPlayerAt(3, 1), opponent);
		
		success = gameField.dropCoin(1, opponent);
		assertTrue(success);
		assertEquals(controller.getPlayerAt(4, 1), opponent);
		
		success = gameField.dropCoin(1, opponent);
		assertTrue(success);
		assertEquals(controller.getPlayerAt(5, 1), opponent);
		assertEquals(controller.getPlayerAt(1, 1), opponent);
	}

	@Test
	public void newGameFieldTest() throws Exception {
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);
		setUp();
		assertEquals(controller.getPlayerAt(0, 0), null);
	}
	
	@Test
	public void isWonTestVertical() throws Exception {
		setUp();
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);

		assertEquals(opponent.toString(), controller.getWinner());
	}

	@Test
	public void isWonTest() throws Exception {
		setUp();

		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, player);
		gameField.dropCoin(0, player);
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);
		assertEquals("", controller.getWinner());

		setUp();

		/**
		 *    0
		 *   0X
		 *  0XX
		 * 0XX00
		 */
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(1, player);
		gameField.dropCoin(1, opponent);
		gameField.dropCoin(2, player);
		gameField.dropCoin(3, opponent);
		gameField.dropCoin(2, player);
		gameField.dropCoin(2, opponent);
		gameField.dropCoin(3, player);
		gameField.dropCoin(4, opponent);
		gameField.dropCoin(3, player);
		gameField.dropCoin(3, opponent);
		assertEquals(opponent.toString(), controller.getWinner());

		setUp();
		
		TUI tui = new TUI(controller);
		
		/**
		 *5 
		 *4 
		 *3   x
		 *2   ox
		 *1   xox 
		 *0  ooxox
		 * 0123456  
		 */
		gameField.dropCoin(6, player);
		gameField.dropCoin(5, opponent);
		gameField.dropCoin(4, player);
		gameField.dropCoin(3, opponent);
		gameField.dropCoin(5, player);
		gameField.dropCoin(4, opponent);
		gameField.dropCoin(3, player);
		gameField.dropCoin(3, opponent);
		gameField.dropCoin(4, player);
		gameField.dropCoin(2, opponent);
		gameField.dropCoin(3, player);

		System.out.println(tui.renderGameField());
		
		assertEquals(player.toString(), controller.getWinner());
		

	}
}