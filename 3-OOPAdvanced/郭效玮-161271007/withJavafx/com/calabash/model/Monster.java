package com.calabash.model;

import com.calabash.view.BattlegroundOverviewController;

public class Monster extends Unit {

	public Monster() {

	}

	public Monster(String url) {
		super(url);
	}

	public String getImgUrl() {
		return BattlegroundOverviewController.imgLocation+imgUrl;
	}

}