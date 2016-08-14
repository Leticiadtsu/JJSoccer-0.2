package models;

/**
 * Essa classe tem a finalidade de definir uma caixa de colisão. A altura e
 * largura são definidas, e no momento de calcular a colisão a localização da
 * caixa é considerada com seu meio igual ao meio do ator que possui a caixa.
 */
public class CollisionArea {

    //Constante de ajuste distancia da colisao
//    public final int  AJUSTE = 2;
    private final Dimensao areaColisao;
    private final Dimensao taxaAproximacao;

    /**
     * Instancia uma nova caixa de Colisao com a altura e largura definida.
     *
     * @param largura largura da caixa.
     * @param altura altura da caixa.
     */
    public CollisionArea(int largura, int altura) {
        areaColisao = new Dimensao(largura, altura);
        taxaAproximacao = new Dimensao(largura * 2, altura * 2);
    }

    /**
     * Verifica de o ator A colide com o ator B.
     *
     * @param actor1 ator referencia.
     * @param actor2 ator alvo.
     * @return true se o ator A colide com B.
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
//                System.err.println(" 1: " + actor1.toString() + " 2: " + actor2.toString());
                return false;
            }
            //actor1 a direita de actor2
            if (getLeftColisionLine(actor1) > getRightColisionLine(actor2)) {
//                System.err.println(" 1: " + actor1.toString() + " 2: " + actor2.toString());
                return false;
            }
            //actor1 a esquerda de actor2
            if (getRightColisionLine(actor1) < getLeftColisionLine(actor2)) {
//                System.err.println(" 1: " + actor1.toString() + " 2: " + actor2.toString());
                return false;
            }
            //se ele nao esta acima nem embaixo nem a esquerda nem a direita entao esta colidindo
//            System.out.println("COLIDIU!!!! 1: " + actor1.toString() + " 2: " + actor2.toString());
            return true;
        }
        return false;

    }

    /**
     * Método que verifica se há uma colisão a partir de um ponto.
     *
     * @param posX posicao em X.
     * @param posY posicao em Y.
     * @param actorAlvo actor que pode haver colisao.
     * @return true se o ponto colide com actorAlvo.
     */
    public boolean isColliding(int posX, int posY, Actor actorAlvo) {
        //esta embaixo actor
        if (posY > getBottonColisionLine(actorAlvo)) {
            //System.err.println(" 1: " + actor1.toString() + " 2: " + actor2.toString());
            return false;
        }
        //esta em cima actor
        if (posY + areaColisao.getAltura() < getTopColisionLine(actorAlvo)) {
            //System.err.println(" 1: " + actor1.toString() + " 2: " + actor2.toString());
            return false;
        }
        //esta a direita de actor
        if (posX > getRightColisionLine(actorAlvo)) {
            //System.err.println(" 1: " + actor1.toString() + " 2: " + actor2.toString());
            return false;
        }
        //esta a esquerda de actor
        if (posX + getAltura() < getLeftColisionLine(actorAlvo)) {

            return false;
        }
        //se ele nao ficara acima nem embaixo nem a esquerda nem a direita entao esta colidindo
        return true;
    }

    /**
     * Método que verifica se um ator está próximo de outro.
     *
     * @param actor1 ator referência.
     * @param actor2 ator alvo.
     * @return verdadeiro, para caso esteja prócimo, ou falso caso contrário.
     */
    public boolean isNear(Actor actor1, Actor actor2) {
        //actor1 embaixo actor2
        if (getTopColisionLine(actor1) - actor1.getCollisionArea().getAltura() * 2 > getBottonColisionLine(actor2)) {
//                System.err.println(" 1: " + actor1.toString() + " 2: " + actor2.toString());
            return false;
        }
        //actor1 em cima actro2
        if (getBottonColisionLine(actor1) + actor1.getCollisionArea().getAltura() * 2 < getTopColisionLine(actor2)) {
//                System.err.println(" 1: " + actor1.toString() + " 2: " + actor2.toString());
            return false;
        }
        //actor1 a direita de actor2
        if (getLeftColisionLine(actor1) - actor1.getCollisionArea().getLargura() * 2 > getRightColisionLine(actor2)) {
//                System.err.println(" 1: " + actor1.toString() + " 2: " + actor2.toString());
            return false;
        }
        //actor1 a esquerda de actor2
        if (getRightColisionLine(actor1) + actor1.getCollisionArea().getLargura() * 2 < getLeftColisionLine(actor2)) {
//                System.err.println(" 1: " + actor1.toString() + " 2: " + actor2.toString());
            return false;
        }
        //se ele nao esta acima nem embaixo nem a esquerda nem a direita entao esta colidindo
//            System.out.println("COLIDIU!!!! 1: " + actor1.toString() + " 2: " + actor2.toString());
        return true;
    }

    /**
     * Retorna a linha de colisão de cima (topo) de um ator.
     *
     * @param actor ator referência.
     * @return retorna a linha de colisão de cima do ator.
     */
    public int getTopColisionLine(Actor actor) {
        return actor.getY();
    }

    /**
     * Retorna a linha de colisão de baixo (botton) de um ator.
     *
     * @param actor ator referência.
     * @return retorna a linha de colisão de baixo do ator.
     */
    public int getBottonColisionLine(Actor actor) {
        return actor.getY() + actor.getCollisionArea().getAltura();
    }

    /**
     * Retorna a linha de colisão à esquerda de um ator.
     *
     * @param actor ator referência.
     * @return retorna a linha de colisão a esquerda do ator.
     */
    public int getLeftColisionLine(Actor actor) {
        return actor.getX();
    }

    /**
     * Retorna a linha de colisão à direita de um ator.
     *
     * @param actor ator referência.
     * @return retorna a linha de colisão à direita do ator.
     */
    public int getRightColisionLine(Actor actor) {
        return actor.getX() + actor.getCollisionArea().getLargura();
    }

    /**
     * Retorna a altura da colisão.
     *
     * @return a altura da caixa de colisão.
     */
    public int getAltura() {
        return areaColisao.getAltura();
    }

    /**
     * Retorna a largura da colisão
     *
     * @return a largura da caixa de colisão.
     */
    public int getLargura() {
        return areaColisao.getLargura();
    }

}
