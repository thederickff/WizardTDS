package com.wizardtdshooter.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.wizardtdshooter.controller.Handler;

public class Wizard extends GameObject {

	private Handler handler;
	private int speed = 3;
	private int width = 32;
	private int height = 48;

	public Wizard(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;
	}

	@Override
	public void tick() {
		this.x += velX;
		this.y += velY;

		movement();
	}

	private void movement() {
		if (handler.isUp()) {
			this.velY = -speed;
		} else if (!handler.isDown()) {
			this.velY = 0;
		}
		if (handler.isDown()) {
			this.velY = speed;
		} else if (!handler.isUp()) {
			this.velY = 0;
		}
		if (handler.isLeft()) {
			this.velX = -speed;
		} else if (!handler.isRight()) {
			this.velX = 0;
		}
		if (handler.isRight()) {
			this.velX = speed;
		} else if (!handler.isLeft()) {
			this.velX = 0;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan.darker());
		g.fillRect(x, y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
