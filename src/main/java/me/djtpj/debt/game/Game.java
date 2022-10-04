package me.djtpj.debt.game;

import io.github.djtpj.JsonPrimitive;
import io.github.djtpj.JsonSave;
import io.github.djtpj.JsonStreamer;
import lombok.Getter;
import lombok.Setter;
import me.djtpj.debt.Command;
import me.djtpj.debt.CommandReader;
import me.djtpj.debt.DebtCounter;
import org.fusesource.jansi.Ansi;

import java.awt.*;

import static me.djtpj.debt.Logger.log;

public class Game {
    private static Game instance;

    @Getter @Setter
    private boolean running = true;

    private Game() {

    }

    public void start() {
        welcome();
        log("wait", 1);
        log("is that right? who would name their clicker game \"Debt\"?", 1);
        log("*shuffling of papers*", 1);
        log("Okay, sure... Welcome to Debt.", 2);

        loop();
    }

    private void welcome() {
        if (DebtCounter.getInstance().getUsedTriggers().length == Command.getAllTriggers().length) {
            String ascii = """
                                        
                    $$$$$$$\\            $$\\        $$\\    \s
                    $$  __$$\\           $$ |       $$ |   \s
                    $$ |  $$ | $$$$$$\\  $$$$$$$\\ $$$$$$\\  \s
                    $$ |  $$ |$$  __$$\\ $$  __$$\\\\_$$  _| \s
                    $$ |  $$ |$$$$$$$$ |$$ |  $$ | $$ |   \s
                    $$ |  $$ |$$   ____|$$ |  $$ | $$ |$$\\\s
                    $$$$$$$  |\\$$$$$$$\\ $$$$$$$  | \\$$$$  |
                    \\_______/  \\_______|\\_______/   \\____/
                    """;
            System.out.println(ascii);
        }

        else {
            System.out.println("Welcome to Debt!");
        }
    }

    private void loop() {
        while (running) {
            log("What would you like to do?", Color.GREEN);

            CommandReader.readAndRunCommand();
        }

        System.out.println("Thanks for playing!");
        log("weirdo... I sure am glad THAT guy is gone", 15);
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
}
