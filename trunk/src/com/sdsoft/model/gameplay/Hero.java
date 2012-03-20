package com.sdsoft.model.gameplay;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ansonbaishuai
 * 
 */
public class Hero {
	int currentHealth;
	int maximumHealth;
	List<Skill> skills = new ArrayList<Skill>();
	String name;
	String cardLocation;
	String smallLocation;
	String bigLocation;

	public String getCardLocation() {
		return cardLocation;
	}

	public void setCardLocation(final String cardLocation) {
		this.cardLocation = cardLocation;
	}

	public String getSmallLocation() {
		return smallLocation;
	}

	public void setSmallLocation(final String smallLocation) {
		this.smallLocation = smallLocation;
	}

	public String getBigLocation() {
		return bigLocation;
	}

	public void setBigLocation(final String bigLocation) {
		this.bigLocation = bigLocation;
	}

	public Hero() {
	}

	public Hero(final int currentHealth, final int maximumHealth, final List<Skill> skills, final String name, final String smallLocation,
			final String bigLocation, final String cardLocation) {
		this.cardLocation = cardLocation;
		this.smallLocation = smallLocation;
		this.bigLocation = bigLocation;
		this.currentHealth = currentHealth;
		this.maximumHealth = maximumHealth;
		this.skills.addAll(skills);
		this.name = name;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(final int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getMaximumHealth() {
		return maximumHealth;
	}

	public void setMaximumHealth(final int maximumHealth) {
		this.maximumHealth = maximumHealth;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(final List<Skill> skills) {
		this.skills = skills;
	}
}
