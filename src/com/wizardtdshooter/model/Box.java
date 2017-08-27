package com.wizardtdshooter.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.wizardtdshooter.view.Window;

public class Box extends GameObject {

	public Box(int x, int y) {
		super(x, y);
		velX = 1;
		velY = -1;
	}

	@Override
	public void tick() {
		this.x += velX;
		this.y += velY;
		if(x > Window.WIDTH || x < 0) {
			velX *= -1;
		}
		if(y > Window.HEIGHT || y < 0) {
			velY *= -1;
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue.darker());
		g.fillRect(x, y, 20, 20);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
