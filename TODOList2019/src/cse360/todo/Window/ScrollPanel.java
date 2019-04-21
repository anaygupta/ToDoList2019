package cse360.todo.Window;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;

public class ScrollPanel extends JPanel implements Scrollable{

	private static final long serialVersionUID = -1660894881429877083L;
	/**
	 * Creates the scroll panel object
	 */
	public ScrollPanel() {}
	/**
	 * Does nothing
	 */
	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return null;
	}
	/**
	 * Always returns 25
	 */
	@Override
	public int getScrollableBlockIncrement(Rectangle arg0, int arg1, int arg2) {
		return 25;
	}
	/**
	 * Returns false
	 */
	@Override
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}
	/**
	 * Returns true
	 */
	@Override
	public boolean getScrollableTracksViewportWidth() {
		return true;
	}
	/**
	 * Returns 25
	 */
	@Override
	public int getScrollableUnitIncrement(Rectangle arg0, int arg1, int arg2) {
		return 25;
	}

}
