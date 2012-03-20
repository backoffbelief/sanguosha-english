package com.sdsoft.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.sdsoft.model.communication.Variables;

public class DoubleClickListener implements MouseListener {
	long lastClick = -1;

	@Override
	public void mouseClicked(final MouseEvent e) {
		if (lastClick < 0) {
			lastClick = System.currentTimeMillis();
		} else {
			final long diff = System.currentTimeMillis() - lastClick;
			if (diff < Variables.DOUBLECLICKSPEED) {
				doubleClicked();
			}
		}
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(final MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(final MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void doubleClicked() {

	}

}
