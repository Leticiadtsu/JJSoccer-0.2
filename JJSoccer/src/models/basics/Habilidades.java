package models.basics;

/**
 * Classe responsável por modelar as habilidades de um jogador. Assim como todas
 * as classes do pacote basics, não interfere no motor e nem na lógica do jogo.
 * Define as habilidade, são elas: velocidade, drible, defesa e específica.
 */
public class Habilidades {

    /**
     * Criação do enum habilidades.
     */
    public enum habilidade {
        Velociadade, Drible, Defesa, Especifica;
    }

    private int velocidade;
    private int drible;
    private int defesa;
    private int especifica;

    /**
     * Construtor das habilidades, atribuit todos os valores recebidos como
     * parâmetro para o enumerador.
     *
     * @param velocidade número que define a velocidade de um jogador.
     * @param drible número que define a capacidade de drible de um jogador.
     * @param defesa número que define a capacidade de defesa de um jogador.
     * @param especial número que define uma habilidade específica de um
     * jogador.
     */
    public Habilidades(int velocidade, int drible, int defesa, int especial) {
        this.initVelocidade(velocidade);
        this.initDrible(drible);
        this.initDefesa(defesa);
        this.initEspecial(especial);
    }

    /**
     * Retorna determinada habilidade do jogador de acordo com o tipo desejado
     * recebido por parâmetro.
     *
     * @param tipo habilidade desejada.
     * @return o número correspondente a habilidade desejada, passada por
     * parâmetro.
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
     * Método responsável por alterar a velocidade do enumerador habilidades.
     * Recebe como parâmetro um novo valor, é verificado se está no intervalo
     * válido e então atribuido
     *
     * @param velocidade novo valor para a velocidade.
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
     * Método responsável por alterar o drible do enumerador habilidades. Recebe
     * como parâmetro um novo valor, é verificado se está no intervalo válido e
     * então atribuido
     *
     * @param drible novo valor para a drible.
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
     * Método responsável por alterar a defesa do enumerador habilidades. Recebe
     * como parâmetro um novo valor, é verificado se está no intervalo válido e
     * então atribuido
     *
     * @param defesa novo valor para a defesa.
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
     * Método responsável por alterar o especial do enumerador habilidades.
     * Recebe como parâmetro um novo valor, é verificado se está no intervalo
     * válido e então atribuido
     *
     * @param especial novo valor para a especial.
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
