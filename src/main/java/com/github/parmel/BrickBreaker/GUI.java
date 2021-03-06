package com.github.parmel.BrickBreaker;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by Grigor Yosifov on 5/15/2014.
 */
public class GUI {

    public static final short offsetX = 2, offsetY = 3;

    private short width, height;

    private byte[][] previousUiData;

    private Terminal terminal;

    public GUI(int width, int height) {
        this.width = (short) width;
        this.height = (short) height;
        initPreviousUiData();
        initScreen();
    }

    public void render(byte[][] uiData, Player player) {
        redrawChangedUiData(uiData);
        redrawStatus(player);
        //printDebug(uiData);
    }

    public void reset() {
    	byte[][] uiData = new byte[this.height][this.width];
        for (int h = 0; h < this.height; h++) {
            for (int w = 0; w < this.width; w++) {
            	uiData[h][w] = 0;
            }
        }
        redrawChangedUiData(uiData);
    }

	public void messageBox(String message) {
		byte x = (byte)((offsetX * 2 + width + 9) / 2);
		byte y = (byte)((offsetY * 2 + height - 2) / 2);
        terminal.moveCursor(x, y);
        for (char ch : message.toCharArray()) {
            terminal.putCharacter(ch);
        }
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
        previousUiData = deepCopy(uiData);
    }

    private void redrawStatus(Player player) {
        String result = String.format("Points: %d | Lives: %d",
        		player.getPoints(), player.getLives());
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

    private static byte[][] deepCopy(byte[][] original) {
        if (original == null) {
            return null;
        }

        final byte[][] result = new byte[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

    private static void printDebug(byte[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }

}
