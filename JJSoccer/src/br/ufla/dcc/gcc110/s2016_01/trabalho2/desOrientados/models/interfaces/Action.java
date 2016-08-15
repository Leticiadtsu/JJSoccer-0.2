package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.interfaces;

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
