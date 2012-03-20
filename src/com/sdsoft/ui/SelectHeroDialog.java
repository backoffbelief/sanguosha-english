package com.sdsoft.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;

import com.sdsoft.model.gameplay.Hero;
import com.sdsoft.model.gameplay.Player;

public class SelectHeroDialog extends JDialog {
	List<HeroLabel> labels = new ArrayList<HeroLabel>();
	private final JButton ok = new JButton("OK");

	public SelectHeroDialog(final Player player, final List<Hero> heros) {

		setTitle("Choose a hero");
		setSize(140 * heros.size() + 10, 250);
		this.setLayout(null);
		for (int i = 0; i < heros.size(); ++i) {
			final HeroLabel label = new HeroLabel(heros.get(i), this);
			label.setLocation(140 * i, 0);
			this.add(label);
			labels.add(label);
		}
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void heroSelected(final HeroLabel label) {
		for (final HeroLabel target : labels) {
			if (label != target) {
				target.deselect();
			}
		}
	}

	// public void selectCharacter(Character character) {
	// owner.setCharacter(character, isLord);
	// dispose();
	// }
}
