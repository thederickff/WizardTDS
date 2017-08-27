package com.wizardtdshooter.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.wizardtdshooter.controller.Handler;
import com.wizardtdshooter.model.Box;

public class Game extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private boolean isRunning = false;
	private Timer timer;
	private Handler handler;
	

	public Game() {
		setFocusable(true);
		setDoubleBuffered(true);
		////////////////////////
		handler = new Handler();
		handler.addObject(new Box(100, 300));
		handler.addObject(new Box(200, 200));
		handler.addObject(new Box(300, 100));
		///////////////////////
		start();
	}

	private void start() {
		isRunning = true;
		timer = new Timer(15, this);
		timer.start();
	}

	private void stop() {
		timer.stop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isRunning) {
			tick();
			repaint();
		} else {
			stop();
		}
	}

	public void tick() {
		handler.tick();
	}

	public void paint(Graphics g) {
		osSupport();
		g.setColor(Color.red.darker().darker());
		g.fillRect(0, 0, 800, 600);
		////////////////////////////////
		handler.render(g);
		////////////////////////////////
		g.dispose();
	}
	
	private void osSupport() {
		if(Window.operatingSystem.equals("Linux")) {
			Toolkit.getDefaultToolkit().sync();
		}
	}
}
