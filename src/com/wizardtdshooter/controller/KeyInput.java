package com.wizardtdshooter.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.wizardtdshooter.model.GameObject;
import com.wizardtdshooter.model.ID;

public class KeyInput extends KeyAdapter {

	private Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				if (key == KeyEvent.VK_W) {
					handler.setUp(true);
				}
				if (key == KeyEvent.VK_S) {
					handler.setDown(true);
				}
				if (key == KeyEvent.VK_A) {
					handler.setLeft(true);
				}
				if (key == KeyEvent.VK_D) {
					handler.setRight(true);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				if (key == KeyEvent.VK_W) {
					handler.setUp(false);
				}
				if (key == KeyEvent.VK_S) {
					handler.setDown(false);
				}
				if (key == KeyEvent.VK_A) {
					handler.setLeft(false);
				}
				if (key == KeyEvent.VK_D) {
					handler.setRight(false);
				}
			}
		}
	}
}
