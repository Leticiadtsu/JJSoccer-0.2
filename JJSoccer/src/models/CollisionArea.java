package models;

import java.awt.Point;

/**
 * Essa classe tem a finalidade de definir uma caixa de colisao A altura e
 * largura sao definidas, e no momento de calcular a colisao a localizacao da
 * caixa eh considerada com seu meia igual ao meio do ator que possui a caixa
 *
 * @author 
 */
public class CollisionArea {

    Dimensao area;

    /**
     * Instancia uma nova caixa de Colisao com a altura e largura definida
     *
     * @param largura largura da caixa
     * @param altura altura da caixa
     */
    public CollisionArea(int largura, int altura) {
        area = new Dimensao(largura, altura);
    }

    /**
     * Verifica de o ator A colide com o ator B
     *
     * @param a ator referencia
     * @param b ator alvo
     * @return true se o ator A colide com B
     */
    public boolean isColliding(Actor a, Actor b) {
        //Acima do ator
        if (getUpperCL(a) < getLowerCL(b)) {
            return false;
        }
        //Ao Lado direito do ator
        if (getEasternCL(a) > getWesternCL(b)) {
            return false;
        }
        //Ao Lado esquerdo do ator
        if (getWesternCL(a) < getEasternCL(b)) {
            return false;
        }
        //Em baixo do ator
        if (getLowerCL(a) > getUpperCL(b)) {
            return false;
        }
        //Se não tiver fora de nenhum dos casos eh que esta colidindo
        return true;
    }

    /**
     * @param a refered actor
     * @return returns the actor's Upper Collision Line
     */
    public int getUpperCL(Actor a) {
        return a.getY() - (a.getCollisionArea().getAltura() / 2);
    }

    /**
     * @param a refered actor
     * @return returns the actor's Lower Collision Line
     */
    public int getLowerCL(Actor a) {
        return a.getY() + (a.getCollisionArea().getAltura() / 2);
    }

    /**
     * @param a refered actor
     * @return returns the actor's Eastern Collision Line
     */
    public int getEasternCL(Actor a) {
        return a.getX() + (a.getCollisionArea().getLargura() / 2);
    }

    /**
     * @param a refered actor
     * @return returns the actor's Western Collision Line
     */
    public int getWesternCL(Actor a) {
        return a.getX() - (a.getCollisionArea().getLargura() / 2);
    }

    /**
     *
     * @return a Altura da caixa de colisao
     */
    public int getAltura() {
        return area.getAltura();
    }

    /**
     *
     * @return a Largura da caixa de colisao
     */
    public int getLargura() {
        return area.getLargura();
    }

}
