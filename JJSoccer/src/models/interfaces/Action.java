package models.interfaces;

import java.awt.Polygon;

/**
 * Interface que representa o conjunto de informações necessarias para o metodo
 * act.
 */
public interface Action {

    /**
     * Informa o poligono que define o limite do mapa, o qual os atores não
     * podem ultrapassar.
     *
     * @return Poligono definido pelo limite do mapa.
     */
    public Polygon getLimite();
}
