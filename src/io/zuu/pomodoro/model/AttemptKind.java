package io.zuu.pomodoro.model;

public enum AttemptKind {
    FOCUS(60 * 25, "F  O  C  U  S"),
    BREAK(60 * 5, "B  R  E  A  K");

    private int TotalSeconds;
    private String DisplayName;


    AttemptKind(int totalSeconds, String displayName) {
        TotalSeconds = totalSeconds;
        DisplayName = displayName;

    }

    public int getTotalSeconds() {
        return TotalSeconds;
    }

    public String getDisplayName() {
        return DisplayName;
    }
}
