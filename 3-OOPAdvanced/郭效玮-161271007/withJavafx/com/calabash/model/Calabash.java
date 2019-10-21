package com.calabash.model;

import com.calabash.view.BattlegroundOverviewController;

public class Calabash extends Unit {

	public Calabash() {

	}

	public Calabash(String url) {
		super(url);
	}

	public String getImgUrl() {
		return BattlegroundOverviewController.imgLocation+imgUrl;
	}
}