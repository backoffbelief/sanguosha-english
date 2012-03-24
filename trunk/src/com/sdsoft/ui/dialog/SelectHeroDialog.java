package com.sdsoft.ui.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.sdsoft.model.gameplay.Hero;
import com.sdsoft.model.gameplay.Player;
import com.sdsoft.ui.drivers.MainUI;
import com.sdsoft.ui.gameplay.HeroLabel;

public class SelectHeroDialog extends JDialog {
	List<HeroLabel> labels = new ArrayList<HeroLabel>();
	private final JButton ok = new JButton("OK");
	private HeroLabel selectedLabel = null;

	public SelectHeroDialog(final Player player, final List<Hero> heros) {
		super(MainUI.getInstance(), "Choose a Hero");
		this.setLayout(new BorderLayout());
		JPanel wrapper = new JPanel();
		setSize(140 * heros.size() + 10, 250);
		wrapper.setLayout(null);
		for (int i = 0; i < heros.size(); ++i) {
			final HeroLabel label = new HeroLabel(heros.get(i), this);
			label.setLocation(140 * i, 0);
			wrapper.add(label);
			labels.add(label);
		}
		this.add(wrapper, BorderLayout.CENTER);
		wrapper = new JPanel();
		wrapper.add(ok);
		this.add(wrapper, BorderLayout.SOUTH);
		setVisible(true);
		ok.setEnabled(false);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void heroSelected(final HeroLabel label) {
		for (final HeroLabel target : labels) {
			if (label != target) {
				target.deselect();
			}
		}
		selectedLabel = label;
		ok.setEnabled(selectedLabel != null);
	}

	public Hero getSelectedHero() {
		Hero result = null;
		if (selectedLabel == null) {
			result = labels.get(0).getHero();
		} else {
			result = selectedLabel.getHero();
		}
		return result;
	}
}
