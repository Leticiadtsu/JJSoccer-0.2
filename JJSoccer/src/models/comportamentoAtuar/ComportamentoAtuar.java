/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models.comportamentoAtuar;

import java.util.List;
import models.Actor;
import models.JogadorActor;
import models.interfaces.Action;

/**
 *
 * @author costa
 */
public interface ComportamentoAtuar {

    /**
     *
     */
    void agir(JogadorActor chamador, Action action, List<Actor> collisions);

    
    
}
