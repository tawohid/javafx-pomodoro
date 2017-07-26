package io.zuu.pomodoro.model;


public class Attempt {
    private int RemaingSeconds;
    private AttemptKind Kind;

    public Attempt(AttemptKind kind, String s) {
        Kind = kind;
        RemaingSeconds = kind.getTotalSeconds();
    }

    public int getRemaingSeconds() {
        return RemaingSeconds;
    }

    public AttemptKind getKind() {
        return Kind;
    }

    public void tick() {
        RemaingSeconds--;
    }

    @Override
    public String toString() {
        return "Attempt{" +
                "RemaingSeconds=" + RemaingSeconds +
                ", Kind=" + Kind +
                '}';
    }

    public void save() {
        System.out.println("Saving..");
    }
}

