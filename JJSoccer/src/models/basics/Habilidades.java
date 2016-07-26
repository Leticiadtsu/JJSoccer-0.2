package models.basics;

/**
 *
 * @author 
 */
public class Habilidades {

    public enum habilidade {
        Velociadade, Drible, Defesa, Especifica;
    }

    private int velocidade;
    private int drible;
    private int defesa;
    private int especifica;

    public Habilidades(int velocidade, int drible, int defesa, int especial) {
        this.initVelocidade(velocidade);
        this.initDrible(drible);
        this.initDefesa(defesa);
        this.initEspecial(especial);
    }

    /**
     * @return the velocidade
     */
    public int getHabilidade(habilidade tipo) {
        if (tipo == habilidade.Velociadade) {
            return this.velocidade;
        } else if (tipo == habilidade.Drible) {
            return this.drible;
        } else if (tipo == habilidade.Defesa) {
            return this.defesa;
        } else if (tipo == habilidade.Especifica) {
            return this.especifica;
        }
        return 0;
    }

    /**
     * @param velocidade the velocidade to set
     */
    private void initVelocidade(int velocidade) {
        if (velocidade > 100) {
            velocidade = 100;
        } else if (velocidade < 0) {
            velocidade = 0;
        }
        this.velocidade = velocidade;
    }

    /**
     * @param drible the drible to set
     */
    private void initDrible(int drible) {
        if (drible > 100) {
            drible = 100;
        } else if (drible < 0) {
            drible = 0;
        }
        this.drible = drible;
    }

    /**
     * @param defesa the defesa to set
     */
    private void initDefesa(int defesa) {
        if (defesa > 100) {
            defesa = 100;
        } else if (defesa < 0) {
            defesa = 0;
        }
        this.defesa = defesa;
    }

    /**
     * @param especial the especial to set
     */
    private void initEspecial(int especial) {
        if (especial > 100) {
            especial = 100;
        } else if (especial < 0) {
            especial = 0;
        }
        this.especifica = especial;
    }

}
