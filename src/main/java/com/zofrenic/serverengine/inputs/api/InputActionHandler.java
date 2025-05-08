package com.zofrenic.serverengine.inputs.api;

public interface InputActionHandler {
    void moveForward();

    void moveBackward();

    void moveLeft();

    void moveRight();

    void jump();

    void sneak();

    void sprint();

    // Métodos para activar o desactivar los movimientos continuos
    void setMovingForward(boolean moving);

    void setMovingBackward(boolean moving);

    void setMovingLeft(boolean moving);

    void setMovingRight(boolean moving);

    // Puedes agregar más métodos según los tipos de input que necesites
}
