package com.sdsoft.model.gameplay;

import com.sdsoft.model.gameplay.CentrolController.Role;

public class Player {
	private String address;
	private int port;
	private String name;
	private int id;
	private int seating;
	private Role role;
	private int currentHealth;
	private int maximumHealth;

	public Role getRole() {
		return role;
	}

	public void setRole(final Role role) {
		this.role = role;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(final int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getMaximumHealth() {
		return maximumHealth;
	}

	public void setMaximumHealth(final int maximumHealth) {
		this.maximumHealth = maximumHealth;
	}

	public int getSeating() {
		return seating;
	}

	public void setSeating(final int seating) {
		this.seating = seating;
	}

	public Player() {

	}

	public Player(final String address, final int port, final String name, final int id) {
		this.address = address;
		this.port = port;
		this.name = name;
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(final int port) {
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Player [address=" + address + ", port=" + port + ", name=" + name + ", id=" + id + "]";
	}

}
