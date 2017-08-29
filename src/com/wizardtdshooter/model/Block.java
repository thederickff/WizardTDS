package com.wizardtdshooter.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wizardtdshooter.controller.SpriteSheet;

public class Block extends GameObject {

	private BufferedImage block_image;
	public Block(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		this.block_image = ss.grabImage(5, 2, 32, 32);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(block_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
