package connectfour.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modelinterfaces.Player;

public class GameControllerTest {
	private Player player1;
	private Player player2;
	
	private Connect4GameController gc;
	
	@Before
	public void setUp() throws Exception {
		gc = new Connect4GameController("Hugo", "Boss");
		
		player1 = gc.player1();
		player2 = gc.player2();
	}

	@After
	public void tearDown() throws Exception {
		/*gc.newGame();
		gc.setOpponend(player1);
		gc.setOpponend(player2);*/
	}

	@Test
	public void getPlayerAtTest() {
		Player p = gc.getPlayerOnTurn();
		gc.dropCoin(0);
		Player test = gc.getPlayerAt(0, 0);
		assertSame(p, test);

		p = null;
		test = null;
		p = gc.getPlayerOnTurn();
		gc.dropCoin(0);
		test = gc.getPlayerAt(1, 0);
		assertSame(p, test);

	}

	@Test
	public void dropCoinTest() {
		Player p;
		
		p = gc.getPlayerAt(0, 0);
		assertNull(p);
		
		gc.dropCoin(0);
		gc.dropCoin(0);
		gc.dropCoin(0);
		gc.dropCoin(0);
		p = gc.getPlayerAt(0, 0);
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
		gc.dropCoin(0);
		gc.dropCoin(0);
		gc.dropCoin(0);
		gc.dropCoin(0);
		
		setUp();
		
		Player p = gc.getPlayerAt(0, 0);
		assertNull(p);
	}

	@Test
	public void undoStepTest() {
		boolean success = true;
		success &= gc.dropCoin(0);
		success &= gc.dropCoin(0);
		success &= gc.dropCoin(0);
		success &= gc.dropCoin(0);
		Player p = gc.getPlayerAt(4, 0);
		assertNull(p);
		success &= gc.dropCoin(0);
		assertTrue(success);
		p = gc.getPlayerAt(4, 0);
		assertNotNull(p);
		gc.undoLastMove();
		p = null;
		p = gc.getPlayerAt(4, 0);
		assertNull(p);
	}

	@Test
	public void redoStepTest() {
		boolean success = true;
		success &= gc.dropCoin(0);
		success &= gc.dropCoin(0);
		success &= gc.dropCoin(0);
		success &= gc.dropCoin(0);
		success &= gc.dropCoin(0);
		assertTrue(success);
		Player p = gc.getPlayerAt(4, 0);
		assertNotNull(p);
		gc.undoLastMove();
		p = null;
		p = gc.getPlayerAt(4, 0);
		assertNull(p);
		
		// TODO Redo
		/*
		gc.redoStep();
		p = null;
		p = gc.getPlayerAt(4, 0);
		assertNotNull(p);*/
	}
}
