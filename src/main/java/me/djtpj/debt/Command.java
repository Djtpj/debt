package me.djtpj.debt;

import lombok.Getter;
import me.djtpj.debt.game.Game;
import org.fusesource.jansi.Ansi;

import java.util.*;

public enum Command {
    EXIT("exit", "quit", "leave", "die", ":3") {
        @Override
        protected boolean onRun(String[] args) {
            Game.getInstance().setRunning(false);
            return true;
        }
    },
    CLICK("click", "uwu", "invest", "clack", "debt") {
        @Override
        protected boolean onRun(String[] args) {
            Logger.log("*click*");

            Random random = new Random();

            int amount = random.nextInt(0, 500);

            debtMessage(amount);

            DebtCounter.getInstance().debt(amount);

            return true;
        }

        private void debtMessage(int amount) {
            if (amount == 0) {
                System.out.println("heh heh");
                Logger.log("HEH heH",3);
            }

            else if (amount < 100) {
                System.out.println(">:(");
            }

            else if (amount < 200) {
                System.out.println("almost debt");
            }

            else if (amount < 300) {
                System.out.println("mm, yes much debt, vary liquid");
            }

            else if (amount < 400) {
                System.out.println("OH yOU dON'T knOW whAT carLSon IS??/?//?!/1");
            }

            else if (amount < 500) {
                System.out.println(">:)");
            }

            else {
                System.out.println("sTOKNKS");
            }
        }
    },
    QUANTITY("quantity", "tasty", "owo") {
        @Override
        protected boolean onRun(String[] args) {
            System.out.println("You're doing good, " + DebtCounter.getInstance().getDebt() + " debt (in metric of course)");

            return true;
        }
    },
    HELP("help", "f2") {
        @Override
        protected boolean onRun(String[] args) {
            Logger.log("What do you need help with?");

            Scanner scanner = new Scanner(System.in);

            scanner.nextLine();

            Logger.log("mmhm, yes I see");
            System.out.println("...");
            Logger.log("OH waIT I doN'T carE", 3);

            return true;
        }
    };

    @Getter
    private final String[] triggers;
    private final int reqArgs;

    Command(int reqArgs, String... triggers) {
        this.triggers = triggers;
        this.reqArgs = reqArgs;
    }

    Command(String... triggers) {
        this.triggers = triggers;
        reqArgs = 0;
    }

    protected abstract boolean onRun(String args[]);

    public boolean run(String usedTrigger, String args[]) {
        if (args.length < reqArgs) return false;

        // Register the used trigger
        DebtCounter.getInstance().addUsedTrigger(usedTrigger);

        return onRun(args);
    }

    public static String[] getAllTriggers() {
        ArrayList<String> results = new ArrayList<>();

        for (Command value : values()) {
            results.addAll(Arrays.asList(value.getTriggers()));
        }

        return results.toArray(new String[0]);
    }
}
