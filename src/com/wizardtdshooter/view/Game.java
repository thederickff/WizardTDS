package com.wizardtdshooter.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.wizardtdshooter.controller.Handler;
import com.wizardtdshooter.controller.KeyInput;
import com.wizardtdshooter.model.ID;
import com.wizardtdshooter.model.Wizard;

public class Game extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private boolean isRunning = false;
	private Timer timer;
	private Handler handler;
	

	public Game() {
		this.setFocusable(true);
		this.setDoubleBuffered(true);
		start();
		////////////////////////
		handler = new Handler();
		handler.addObject(new Wizard(10, 10, ID.Player, handler));

		///////////////////////
		this.addKeyListener(new KeyInput(handler));
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
		g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
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
