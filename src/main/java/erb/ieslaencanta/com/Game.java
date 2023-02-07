package erb.ieslaencanta.com;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Terminal terminal;
    private Screen screen;
    private boolean exit_key;

    public Game() {
        this.exit_key = false;
        try {
            this.terminal = new DefaultTerminalFactory().createTerminal();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            this.screen = new TerminalScreen(this.terminal);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        screen.setCursorPosition(null);
    }

    public void loop() {
        int x, y;
        try {
            screen.startScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        screen.clear();
        while (!this.exit_key) {
            //se procesa la entrada

            x=(int) Math.random()*80;
            y=(int) Math.random()*24;
            process_input();
        }
        try {
            screen.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            terminal.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void process_input() {
        KeyStroke keyStroke = null;
        try {
            keyStroke = screen.pollInput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (keyStroke != null) {
            if (keyStroke.getKeyType() == KeyType.Escape) {
                this.exit_key = true;
            }
        }

    }

}
