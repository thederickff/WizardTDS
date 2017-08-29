package com.wizardtdshooter.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Crate extends GameObject {

	public Crate(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.orange.darker());
		g.fillRect(x, y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}
