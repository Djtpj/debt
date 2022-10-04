package me.djtpj.debt;

import me.djtpj.debt.game.Game;
import org.fusesource.jansi.AnsiConsole;

public class Main {
    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        Game.getInstance().start();
    }
}
