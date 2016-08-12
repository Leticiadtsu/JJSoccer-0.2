package models;

/**
 * Essa classe tem a finalidade de definir uma caixa de colisao A altura e
 * largura sao definidas, e no momento de calcular a colisao a localizacao da
 * caixa eh considerada com seu meia igual ao meio do ator que possui a caixa
 *
 * @author
 */
public class CollisionArea {

    //Constante de ajuste distancia da colisao
//    public final int  AJUSTE = 2;
    private final Dimensao area;

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
     * @param actor1 ator referencia
     * @param actor2 ator alvo
     * @return true se o ator A colide com B
     */
    public boolean isColliding(Actor actor1, Actor actor2) {
        //actor1 embaixo actor2
        if (actor1 != actor2) {
            if (getTopColisionLine(actor1) > getBottonColisionLine(actor2)) {
//                System.err.println(" 1: " + actor1.toString() + " 2: " + actor2.toString());
                return false;
            }
            //actor1 em cima actro2
            if (getBottonColisionLine(actor1) < getTopColisionLine(actor2)) {
                System.err.println(" 1: " + actor1.toString() + " 2: " + actor2.toString());
                return false;
            }
            //actor1 a direita de actor2
            if (getLeftColisionLine(actor1) > getRightColisionLine(actor2)) {
//                System.err.println(" 1: " + actor1.toString() + " 2: " + actor2.toString());
                return false;
            }
            //actor1 a esquerda de actor2
            if (getRightColisionLine(actor1) < getLeftColisionLine(actor2)) {
                System.err.println(" 1: " + actor1.toString() + " 2: " + actor2.toString());
                return false;
            }
            //se ele nao esta acima nem embaixo nem a esquerda nem a direita entao esta colidindo
            System.out.println("COLIDIU!!!! 1: " + actor1.toString() + " 2: " + actor2.toString());
            return true;
        }
        return false;

    }

    /**
     * @param actor refered actor
     * @return returns the actor's Upper Collision Line
     */
    public int getTopColisionLine(Actor actor) {
        return actor.getY();
    }

    /**
     * @param actor refered actor
     * @return returns the actor's Lower Collision Line
     */
    public int getBottonColisionLine(Actor actor) {
        return actor.getY() + actor.getCollisionArea().getAltura();
    }

    /**
     * @param actor refered actor
     * @return returns the actor's Eastern Collision Line
     */
    public int getLeftColisionLine(Actor actor) {
        return actor.getX();
    }

    /**
     * @param actor refered actor
     * @return returns the actor's Western Collision Line
     */
    public int getRightColisionLine(Actor actor) {
        return actor.getX() + actor.getCollisionArea().getLargura();
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
