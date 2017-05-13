package com.example.dourado_dtona.jogo_velha;

import android.app.AlertDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class PvP extends AppCompatActivity {
    public TextView jogada;
    private View view;
    //TODO variavel usada para representar o circulo
    public final String bola = "O";
    //TODO variavel usada para representar o xis
    public final String xis = "X";
    //TODO variavel usada para pegar a ultima letra jogada
    public String ultimaLetra = "O";
    public  EditText jogador1;
    public EditText jogador2;
	public int nJogadas;
    //TODO Array com matriz para verificar se o jogo acabou
    int[][] estadofinal = new int[][]{
            //TODO verificacao na horizontal;
            {1,2,3},
            {4,5,6},
            {7,8,9},

            //TODO verificacao na vertical;
            {1,4,7},
            {2,5,8},
            {3,6,9},

            //TODO verificacao na diagonal;
            {1,5,9},
            {3,5,7}
    };
    public View getView(){
        return view;
    }
    public void setView(View view){
        this.view = view;
    }
    public String getUltimaLetra(){
        return ultimaLetra;
    }
    public void setUltimaLetra(String ultimaLetra){
        this.ultimaLetra = ultimaLetra;
    }
    public Button getTag(int btnNum){
        return (Button)getView().findViewWithTag("btn"+btnNum);
    }
    public  void setColorBtn(int btn){
        getTag(btn).setTextColor(Color.GREEN );
    }
    public void habilitaBtn(boolean b){

        //Array para pegar todos botões por TAG
        for (int i=1; i<=9; i++){
            if(getTag(i) !=null){
                getTag(i).setEnabled( b );
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_pv_p);
        setView(getLayoutInflater().inflate(R.layout.activity_pv_p,null));
        setContentView(getView());
        jogada = (TextView)findViewById(R.id.jogada);
        jogador1 = (EditText)findViewById(R.id.jogador1);
        jogador2 = (EditText)findViewById(R.id.jogador2);//Sempre inicia como jogador 1
    }

    public void btnNomes(View v){
        if (jogador1.getText().toString().trim().isEmpty()){
            jogador1.setError("Preencha este campo");
        }
        if (jogador2.getText().toString().trim().isEmpty()){
            jogador2.setError("Preencha este campo");
        }else{
            jogador1.setVisibility(View.GONE);
            jogador2.setVisibility(View.GONE);
            jogada.setText("Rodada do(a): "+jogador1.getText().toString());
            ((Button)findViewById(R.id.btnNomes)).setVisibility(View.GONE);
        }
    }
    //TODO pega o botão que foi clicado e acrescenta a letra X/O;
    public void btn(View view){

        //TODO Verifica se o Botao esta vazio
        if(((Button)view).getText().equals("")){

            //TODO verifica qual foi a última letra(X/O) jogada, e altera para a seguinte;
            if(getUltimaLetra().equals(xis)) {
                jogada.setText("Rodada do(a): "+jogador1.getText().toString()); //Mostra qual jogador está na vez
                ((Button)view).setTextColor(Color.BLUE); //Cor da letra
                ((Button)view).setText(bola); //Próxima letra
                ((Button)view).setEnabled(false);
                setUltimaLetra(bola);
            }else{
                jogada.setText("Rodada do(a): "+jogador2.getText().toString());
                ((Button)view).setTextColor(Color.RED);			
                ((Button)view).setText(xis);
                ((Button)view).setEnabled(false);
                setUltimaLetra(xis);
            }	
			nJogadas++;
            fim();
        }else {
            Toast.makeText(getView().getContext(),"Campo já foi preenchido, tente outro!", Toast.LENGTH_SHORT).show();
        }
    }

    public void fim(){
        String vencedor = null;
        for(int x=0; x<=7; x++){
            //pega as possibilidades de vencer o jogo e armazena
            String s1 = getTag(estadofinal[x][0]).getText().toString();
            String s2 = getTag(estadofinal[x][1]).getText().toString();
            String s3 = getTag(estadofinal[x][2]).getText().toString();

            //TODO Nao verifica caso o campo seja vazio
            if((!s1.equals("")) &&
               (!s2.equals("")) &&
               (!s3.equals("")) ) {

                if (s1.equals(s2) && s2.equals(s3)) {
                    for (int i=1; i<=9; i++){
                        if(getTag(i) !=null){
                            //TODO Altera a cor do texto dos botoes para preto
                            getTag(i).setTextColor(Color.BLACK);
                        }
                    }

                    habilitaBtn( false ); //desabilita os botoes
                    jogada.setText("Fim de jogo");

                    //TODO Altera a cor do texto dos botoes que venceram para verde
                    setColorBtn( estadofinal[x][0]);
                    setColorBtn( estadofinal[x][1]);
                    setColorBtn( estadofinal[x][2]);

                    //TODO verifica qual foi o vencedor
                    if(getUltimaLetra().equals("X")){
                        vencedor = jogador1.getText().toString();
                    }else if(getUltimaLetra().equals("O")){
                        vencedor = jogador2.getText().toString();
                    }
                    //TODO emite quem ganhou o jogo
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setMessage("Vitória do "+vencedor+" em "+nJogadas+" jogadas, PARABÉNS!");
                    alert.setNeutralButton("OK", null);
                    alert.show();
                }
            }
        }
    }

    //TODO reseta o jogo para iniciar novamente;
    public void novoJogo(View v){
        for (int i=1; i<=9; i++){
            if(getTag(i) != null){
                getTag(i).setText("");
                jogada.setText("Rodada do(a): "+jogador1.getText().toString());
                nJogadas = 0;
            }
        }
        habilitaBtn( true );
    }
}
