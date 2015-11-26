/**
 * 
 */
package connectfour.ui.tui;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import connectfour.controller.Connect4GameController;

/**
 * @author Stefano Di Martino
 */
public class TUITest {

    private Connect4GameController controller;

	@Before
	public void setUp() throws Exception {
		controller = new Connect4GameController("Hugo", "Boss");
	}

	@Test
	public void dropCointTest1() {
		boolean success = true;
		success &= this.controller.dropCoin(3);
		success &= this.controller.dropCoin(3);
		success &= this.controller.dropCoin(1);
		success &= this.controller.dropCoin(1);
		success &= this.controller.dropCoin(1);
		success &= this.controller.dropCoin(1);
		success &= this.controller.dropCoin(1);
		success &= this.controller.dropCoin(1);

		assertTrue(success);
	}
}