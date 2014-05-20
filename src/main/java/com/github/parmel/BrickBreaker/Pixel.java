package com.github.parmel.BrickBreaker;

import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;

/**
 * Created by Grigor Yosifov on 5/15/2014.
 */
public class Pixel {

	public static Color getBackgroundColor(byte pixelCode) {
		switch (pixelCode) {
		case -1:
			// ball
			return Terminal.Color.GREEN;
		case -2:
			// springboard
			return Terminal.Color.RED;
		case 1:
			return Terminal.Color.GREEN;
		case 2:
			return Terminal.Color.CYAN;
		case 3:
			return Terminal.Color.MAGENTA;
		case 4:
			return Terminal.Color.YELLOW;
		case 5:
			return Terminal.Color.RED;
		default:
			return Terminal.Color.DEFAULT;
		}
	}

	public static Color getForegroundColor(byte pixelCode) {
		return Terminal.Color.DEFAULT;
	}

	public static char getFirstCharacter(byte pixelCode) {
		return ' ';
	}

	public static char getSecondCharacter(byte pixelCode) {
		return ' ';
	}

}
