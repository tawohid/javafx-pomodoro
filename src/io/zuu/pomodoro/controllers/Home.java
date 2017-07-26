package io.zuu.pomodoro.controllers;

import io.zuu.pomodoro.model.Attempt;
import io.zuu.pomodoro.model.AttemptKind;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.sql.Time;


public class Home {
    private final AudioClip Notified;
    @FXML private VBox container;
    @FXML private Label title;


    private Attempt CurrentAttempt;
    private StringProperty TimerText;
    private Timeline Timeline;

    public Home() {
        TimerText = new SimpleStringProperty();
        setTimerText(0);
        Notified = new AudioClip(getClass().getResource("/sounds/not.wav").toExternalForm());
    }

    public String getTimerText() {
        return TimerText.get();
    }

    public StringProperty timerTextProperty() {
        return TimerText;
    }

    public void setTimerText(String timerText) {
        this.TimerText.set(timerText);
    }

    public void setTimerText(int remainingSeconds) {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;

        setTimerText(String.format("%02d:%02d", minutes, seconds));

    }

    private void prepareAttempt(AttemptKind kind) {
        reset();
        CurrentAttempt = new Attempt(kind, "");
        addAttemptStyle(kind);
        title.setText(kind.getDisplayName());
        setTimerText(CurrentAttempt.getRemaingSeconds());
        Timeline = new Timeline();
        Timeline.setCycleCount(kind.getTotalSeconds());
        Timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            CurrentAttempt.tick();
            setTimerText(CurrentAttempt.getRemaingSeconds());
        }));
        Timeline.setOnFinished(e -> {
            saveCurrentAttempt();
            Notified.play();
            prepareAttempt(CurrentAttempt.getKind() == AttemptKind.FOCUS ?
                    AttemptKind.BREAK : AttemptKind.FOCUS );
        });
    }

    private void saveCurrentAttempt() {
        CurrentAttempt.save();
    }

    private void reset() {
        clearAttemptStyle();
        if (Timeline != null && Timeline.getStatus() == Animation.Status.RUNNING) {
            Timeline.stop();
        }
    }

    public void playTimer() {
        container.getStyleClass().add("playing");
         Timeline.play();
     }

     public void pauseTimer() {
        container.getStyleClass().remove("playing");
        Timeline.pause();
     }

    private void addAttemptStyle(AttemptKind kind) {
        container.getStyleClass().add(kind.toString().toLowerCase());
    }

    private void clearAttemptStyle() {
        container.getStyleClass().remove("playing");
        for (AttemptKind kind : AttemptKind.values()) {
            container.getStyleClass().remove(kind.toString().toLowerCase());
        }
    }

    public void handleRestart(ActionEvent actionEvent) {
        prepareAttempt(AttemptKind.FOCUS);
        playTimer();
    }

    public void handlePlay(ActionEvent actionEvent) {
        if (CurrentAttempt == null) {
            handleRestart(actionEvent);
        } else {
            playTimer();
        }

    }

    public void handlePause(ActionEvent actionEvent) {
        pauseTimer();
    }
}

