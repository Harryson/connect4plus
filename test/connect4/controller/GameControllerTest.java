package connect4.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import connectfour.controller.GameController;
import connectfour.model.Human;
import connectfour.model.Player;

public class GameControllerTest {
	private Human player1;
	private Player player2;
	private int row;
	GameController gc;

	@Before
	public void setUp() throws Exception {
		gc = GameController.getInstance();
		gc.newGame();
		player1 = new Human();
		player2 = new Human();
		row = 0;
		gc.setPlayer(player1);
		gc.setOpponend(player2);
	}

	@After
	public void tearDown() throws Exception {
		gc.newGame();
		row = 0;
		player1 = new Human();
		player2 = new Human();
		gc.setPlayer(player1);
		gc.setOpponend(player2);
	}

	@Test
	public void test() {
		assertTrue(player1 != null);
		assertTrue(player2 != null);
		assertEquals(0, row);
	}

	@Test
	public void getPlayerOnTurnTest() {

	}

	@Test
	public void getPlayerAtTest() {
		Player p = gc.getPlayerOnTurn();
		gc.dropCoinWithSuccessFeedback(0);
		Player test = gc.getPlayerAt(0, 0);
		assertSame(p, test);

		p = null;
		test = null;
		p = gc.getPlayerOnTurn();
		gc.dropCoinWithSuccessFeedback(0);
		test = gc.getPlayerAt(1, 0);
		assertSame(p, test);

	}

	@Test
	public void dropCoinWithSuccessFeedbackTest() {
		gc.dropCoinWithSuccessFeedback(0);
		gc.dropCoinWithSuccessFeedback(0);
		gc.dropCoinWithSuccessFeedback(0);
		gc.dropCoinWithSuccessFeedback(0);
		Player p = gc.getPlayerAt(0, 0);
		System.out.println(gc.getGameField());
		assertNotNull(p);
		p = null;
		p = gc.getPlayerAt(1, 0);
		assertNotNull(p);
		p = null;
		p = gc.getPlayerAt(2, 0);
		assertNotNull(p);
		p = null;
		p = gc.getPlayerAt(3, 0);
		assertNotNull(p);
		p = null;
		p = gc.getPlayerAt(4, 0);
		assertNull(p);

	}

	@Test
	public void newGameTest() throws Exception {
		gc.dropCoinWithSuccessFeedback(0);
		gc.dropCoinWithSuccessFeedback(0);
		gc.dropCoinWithSuccessFeedback(0);
		gc.dropCoinWithSuccessFeedback(0);
		gc.newGame();
		Player p = gc.getPlayerAt(0, 0);
		assertNull(p);
	}

	@Test
	public void undoStepTest() {
		System.out.println("Undo test");
		boolean success = true;
		success &= gc.dropCoinWithSuccessFeedback(0);
		success &= gc.dropCoinWithSuccessFeedback(0);
		success &= gc.dropCoinWithSuccessFeedback(0);
		success &= gc.dropCoinWithSuccessFeedback(0);
		System.out.println(gc.getGameField());
		assertTrue(success);
		Player p = gc.getGameField().getPlayerAt(4, 0);
		assertNotNull(p);
		gc.undoStep();
		System.out.println(gc.getGameField());
		p = null;
		p = gc.getGameField().getPlayerAt(4, 0);
		assertNull(p);
	}

	@Test
	public void redoStepTest() {
		System.out.println("Undo test");
		boolean success = true;
		success &= gc.dropCoinWithSuccessFeedback(0);
		success &= gc.dropCoinWithSuccessFeedback(0);
		success &= gc.dropCoinWithSuccessFeedback(0);
		success &= gc.dropCoinWithSuccessFeedback(0);
		assertTrue(success);
		Player p = gc.getGameField().getPlayerAt(4, 0);
		assertNotNull(p);
		gc.undoStep();
		System.out.println(gc.getGameField());
		p = null;
		p = gc.getGameField().getPlayerAt(4, 0);
		assertNull(p);
		gc.redoStep();
		p = null;
		p = gc.getGameField().getPlayerAt(4, 0);
		assertNotNull(p);
	}

}
