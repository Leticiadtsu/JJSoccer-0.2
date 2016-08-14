/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models.interfaces;

import controller.managers.GameScene;

/**
 * Interface que representa aqueles que ouvem quando há um requerimento de
 * mudança de scene. Utilizado no contexto do Game e suas GameScenes.
 */
public interface ChangeSceneListener {

    /**
     * Requerimento de mudança de cena
     *
     * @param scene cena requeria para que seja atribuida.
     */
    public void changeScene(GameScene scene);

}
