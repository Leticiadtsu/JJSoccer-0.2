package models.basics;

import java.util.Random;

/**
 * 
 * @author 
 */
public class Jogador {
     private static final Random rand = new Random();
    
    private int id;
    private String nome;
    private Habilidades habilidades;

    public Jogador(String nome){
        this.nome = nome;
        this.habilidades = new Habilidades(rand.nextInt(100) + 1, rand.nextInt(100) + 1, rand.nextInt(100) + 1, rand.nextInt(100) + 1);
    }
    
    
    public Jogador(String nome, int velocidade, int drible, int defesa, int especifica) {
        this.id = id;
        this.nome = nome;
        this.habilidades = new Habilidades(velocidade, drible, defesa, especifica);
    }   
    
    public String getNome(){
        return this.nome;
    }
    
    public int getHabilidade(Habilidades.habilidade tipo){
        return habilidades.getHabilidade(tipo);
    }
}
