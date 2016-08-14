package models;

import java.util.ArrayList;
import java.util.List;
import models.interfaces.Action;
import models.interfaces.GolListener;

/**
 * Classe que representa o gol do jogo. Possui uma lista de ouvintes e o um
 * atributo booleano que representa se o gol é do time casa ou não. Commo herda
 * de Actor, é uma classe renderizável.
 */
public class Gol extends Actor {

    private List<GolListener> listeners;
    private boolean timeCasa;

    /**
     * Construtor que recebe como parâmetro um ouvinte e a informação se o gol é
     * do time casa.
     *
     * @param listener ouvinte.
     * @param timeCasa verdadeiro se o gol é do time casa, ou falso caso
     * contrário.
     */
    public Gol(GolListener listener, boolean timeCasa) {
        sprite = new Sprite("gol.png");
        setCollisionArea(new CollisionArea(80, 200));
        this.listeners = new ArrayList<>();
        this.listeners.add(listener);
        this.timeCasa = timeCasa;
    }

    /**
     * Construtor que recebe como parâmetro um ouvinte, a informação se o gol é
     * do time casa e a as posições x e y.
     *
     * @param x
     * @param y
     * @param listener ouvinte.
     * @param timeCasa verdadeiro se o gol é do time casa, ou falso caso
     * contrário.
     */
    public Gol(int x, int y, GolListener listener, boolean timeCasa) {
        super(x, y);
        sprite = new Sprite("gol.png");
        setCollisionArea(new CollisionArea(80, 200));
        this.listeners = new ArrayList<>();
        this.listeners.add(listener);
        this.timeCasa = timeCasa;
    }

    /**
     * Sobreescrita do método act de Actor, o qual indica se houve ou não um
     * gol.
     *
     * @param action dados necessários para a ação.
     * @param areaDeRelevancia lista de atores que colidem com ele.
     */
    @Override
    public void act(Action action, List<Actor> areaDeRelevancia) {
        for (Actor actor : areaDeRelevancia) {
            if (getCollisionArea().isColliding(this, actor)) {
                if (actor instanceof Bola) {
                    for (GolListener listener : listeners) {
                        listener.onGoal(isTimeCasa());
                    }

                }
            }
        }
    }

    /**
     * Adiciona um ouvinte na lista de ouvintes do gol.
     *
     * @param listener ouvinte.
     */
    public void addListener(GolListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Retorna se o o gol é do time casa, ou não.
     *
     * @return verdadeiro, se o o gol é do time casa, ou falso caso contrário.
     */
    public boolean isTimeCasa() {
        return timeCasa;
    }

}
