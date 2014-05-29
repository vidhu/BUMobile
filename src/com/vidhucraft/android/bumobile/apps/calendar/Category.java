package com.vidhucraft.android.bumobile.apps.calendar;

public class Category {
	private String title;
	private String name;

	public Category(String title, String name) {
		this.title = title;
		this.name = name;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
