package com.sdsoft.ui.gameplay;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import com.sdsoft.model.gameplay.Hero;
import com.sdsoft.ui.dialog.SelectHeroDialog;
import com.sdsoft.util.ImageUtil;

public class HeroLabel extends JLabel implements MouseMotionListener, MouseListener {

	private final SelectHeroDialog dialog;
	private final Hero hero;
	private int posX, posY;

	public HeroLabel(final Hero hero, final SelectHeroDialog dialog) {
		super(ImageUtil.getIcon(hero.getCardLocation()));
		this.hero = hero;
		this.dialog = dialog;
		setName("F");
		setOpaque(false);
		setSize(130, 188);
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public void deselect() {
		this.setBorder(BorderFactory.createEmptyBorder());
	}

	@Override
	public void mouseClicked(final MouseEvent e) {

	}

	public void setPosition(final int x, final int y) {
		setLocation(x, y);
		posX = x;
		posY = y;
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		final JLabel label = (JLabel) e.getSource();
		label.setBorder(new LineBorder(Color.GREEN, 4));
		dialog.heroSelected(this);
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		try {
			Thread.sleep(100);
		} catch (final InterruptedException e1) {
			e1.printStackTrace();
		}
		// owner.selectCharacter(character);
	}

	@Override
	public void mouseEntered(final MouseEvent e) {
	}

	@Override
	public void mouseExited(final MouseEvent e) {
		// final JLabel label = (JLabel) e.getSource();
		// if (label.getName().equals("F")) {
		// label.setLocation(posX, posY);
		// }
	}

	@Override
	public void mouseDragged(final MouseEvent e) {
	}

	@Override
	public void mouseMoved(final MouseEvent e) {
		// final JLabel label = (JLabel) e.getSource();
		// final int offsetX = (int) ((e.getX() - 65) / 65. * 40);
		// final int offsetY = (int) ((e.getY() - 98) / 98. * 40);
		//
		// if (label.getName().equals("F")) {
		// label.setLocation(posX + offsetX, posY + offsetY);
		// // label.updateUI();
		// }
	}
}
