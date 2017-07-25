package io.zuu.pomodoro.model;


public class Attempt {
    private int RemaingSeconds;
    private AttemptKind Kind;

    public Attempt(AttemptKind kind) {
        Kind = kind;
        RemaingSeconds = kind.getTotalSeconds();
    }

    public int getRemaingSeconds() {
        return RemaingSeconds;
    }

    public AttemptKind getKind() {
        return Kind;
    }
}

