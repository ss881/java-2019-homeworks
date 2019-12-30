package com.calabash.model;

import com.calabash.view.BattlegroundOverviewController;

public abstract class Unit {
	protected String imgUrl;

	public Unit() {

	}

	public Unit(String url) {
		this.imgUrl=url;
	}

	public String getImgUrl() {
		return BattlegroundOverviewController.imgLocation+imgUrl;
	}
}

