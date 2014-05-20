package com.github.parmel.BrickBreaker;

import java.nio.charset.Charset;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by Grigor Yosifov on 5/15/2014.
 */
public class UserInterface {
	
	public static final short offsetX = 2, offsetY = 3;
	
	private short width, height;
	
	private byte[][] previousUiData;
	
	private Terminal terminal;

	public UserInterface(int width, int height) {
		this.width = (short) width;
		this.height = (short) height;
		initPreviousUiData();
	    initScreen();
	}

	public void render(byte[][] uiData, Status status) {
		redrawChangedUiData(uiData);
		redrawStatus(status);
	}

	public Terminal getTerminal() {
		return terminal;
	}
	
	private void initPreviousUiData() {
		this.previousUiData = new byte[this.height][this.width];
		for (int h = 0; h < this.height; h++) {
			for (int w = 0; w < this.width; w++) {
				this.previousUiData[h][w] = 0;
			}
		}
	}

	private void initScreen() {
		this.terminal = TerminalFacade.createTerminal(Charset.forName("UTF8"));
		this.terminal.enterPrivateMode();
		this.terminal.setCursorVisible(false);
		initScreenFrame();
//	    TerminalSize screenSize = this.terminal.getTerminalSize();
//		if (screenSize.getColumns() < this.width
//				|| screenSize.getRows() < this.height) {
//			// TODO Print Error - Terminal window is too small
//		}
	}

	private void initScreenFrame() {
		for (int i = 0; i < (offsetY + this.height + 1); i++) {
			drawVerticalFrame(0, i);
			drawVerticalFrame(offsetX + this.width * 2, i);
		}
		for (int i = 0; i < (offsetX * 2 + this.width * 2); i++) {
			drawHorizontalFrame(i, 0);
			drawHorizontalFrame(i, offsetY - 1);
			drawHorizontalFrame(i, offsetY + this.height);
		}
	}

	private void drawVerticalFrame(int x, int y) {
		terminal.moveCursor(x, y);
		terminal.applySGR(Terminal.SGR.ENTER_BOLD);
		terminal.putCharacter('|');
		terminal.putCharacter('|');
		terminal.applySGR(Terminal.SGR.EXIT_BOLD);
	}

	private void drawHorizontalFrame(int x, int y) {
		terminal.moveCursor(x, y);
		terminal.applySGR(Terminal.SGR.ENTER_BOLD);
		terminal.putCharacter('=');
		terminal.applySGR(Terminal.SGR.EXIT_BOLD);
	}

	private void redrawChangedUiData(byte[][] uiData) {
		for (int h = 0; h < uiData.length; h++) {
			for (int w = 0; w < uiData[h].length; w++) {
				if (this.previousUiData[h][w] != uiData[h][w]) {
					drawPixel(uiData[h][w], h, w);
				}
			}
		}
	}
	
	private void redrawStatus(Status status) {
		String result = String.format("Points: %d | Lives: %d",
				status.getPoints(), status.getLives());
		this.terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
		terminal.moveCursor(offsetX + 1, 1);
		for (char ch : result.toCharArray()) {
			terminal.putCharacter(ch);
		}
	}

	private void drawPixel(byte pixelCode, int y, int x) {
		this.terminal.moveCursor(offsetX + (x * 2), offsetY + y);
		this.terminal.applyBackgroundColor(Pixel.getBackgroundColor(pixelCode));
		this.terminal.applyForegroundColor(Pixel.getForegroundColor(pixelCode));
		terminal.putCharacter(Pixel.getFirstCharacter(pixelCode));
		terminal.putCharacter(Pixel.getSecondCharacter(pixelCode));
	}

}
