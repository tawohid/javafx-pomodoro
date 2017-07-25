package io.zuu.pomodoro.model;

public enum AttemptKind {
    FOCUS(60 * 25), BREAK(60 * 5);

    private int TotalSeconds;


    AttemptKind(int totalSeconds) {
        TotalSeconds = totalSeconds;
    }

    public int getTotalSeconds() {
        return TotalSeconds;
    }
}
