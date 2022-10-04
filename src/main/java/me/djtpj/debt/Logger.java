package me.djtpj.debt;

import org.fusesource.jansi.Ansi;

import java.awt.*;

import static org.fusesource.jansi.Ansi.ansi;

public class Logger {
    private Logger() {
        throw new IllegalStateException("Utility Class");
    }

    public static void log(String msg, Color color) {
        log(msg, color, 0);
    }

    public static void log(String msg,  int delaySeconds) {
        log(msg, null, delaySeconds);
    }

    public static void log(String msg) {
        log(msg, null, 0);
    }

    public static void log(String msg, Color color, int delaySeconds) {
        try {
            Thread.sleep(delaySeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Ansi ansi = ansi();

        if (color != null) {
            ansi.fgRgb(color.getRed(), color.getGreen(), color.getBlue());
        }

        ansi.a(msg);

        System.out.println(ansi.reset());
    }
}
