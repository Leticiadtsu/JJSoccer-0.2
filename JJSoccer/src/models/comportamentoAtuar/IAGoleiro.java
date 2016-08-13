/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models.comportamentoAtuar;

import java.util.List;
import models.Actor;
import models.Actor.Direcao;
import models.Dimensao;
import models.JogadorActor;
import models.interfaces.Action;

/**
 *
 * @author costa
 */
public class IAGoleiro extends InteligenciaArtificial{
    private Direcao direcao;

    public IAGoleiro() {
        direcao = Direcao.BAIXO;
    }
    
    @Override
    public void agir(JogadorActor chamador, Action action, List<Actor> collisions) {
        escolherDirecao(chamador);
        chamador.mover(direcao, new Dimensao(15000,15000), collisions);
    }
    
    private void escolherDirecao(JogadorActor chamador){
        if(chamador.getY()>700){
            this.direcao = Direcao.CIMA;
        }
        if(chamador.getY()<10){
            this.direcao = Direcao.BAIXO;
        }
    }

    
    
}
