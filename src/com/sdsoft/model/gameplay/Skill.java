package com.sdsoft.model.gameplay;

import com.sdsoft.model.communication.JSONUtil;

public class Skill {
	private String name;

	public Skill() {

	}

	public void setName(String name) {
		this.name = name;
	}

	public Skill(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static void main(String[] args) {
		Skill skill = new Skill("test");
		System.out.println(JSONUtil.convertToString(skill));
	}
}
