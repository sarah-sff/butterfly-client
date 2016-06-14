package swingView;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import swingView.define.ButterPanel;

public class Panel extends ButterPanel {
	TopPanel topPanel = new TopPanel();
	LightPanel lightPanel = new LightPanel();
	NumberPanel numberPanel = new NumberPanel();
	ButtonPanel buttonPanel = new ButtonPanel();

	public Panel() {
		setBackground(Color.red);
		setLayout(new GridLayout(4, 1));// 网格布局，3行1列
		add(topPanel);
		add(lightPanel);
		add(numberPanel);
		add(buttonPanel);
	}
	
}
