package br.senai.sc.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String numeroDigitado = "";
    private ArrayList<Float> numeros = new ArrayList<>();
    private ArrayList<String> operacoes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Calculadora MaraviTop");
        display = findViewById(R.id.tv_display);
    }

    private void atualizarDisplay(String texto) {
        String textoDisplay = display.getText().toString();
        textoDisplay = textoDisplay + texto;
        display.setText(textoDisplay);
    }

    private void atualizarNumeroDigitado(String numero) {
        numeroDigitado = numeroDigitado + numero;
    }

    private void novoCalculo (String operacao) {
        if (numeroDigitado.equals("")) {
            Toast.makeText(MainActivity.this, "Por favor, insira um número válido", Toast.LENGTH_LONG).show();
        } else {
            numeros.add(Float.parseFloat(numeroDigitado));
            numeroDigitado = "";
            operacoes.add(operacao);
            atualizarDisplay(operacao);
        }
    }
    private float adicao(int posicao) {
        float resultado = numeros.get(posicao) + numeros.get(posicao + 1);
        numeros.set(posicao, resultado);
        numeros.remove(posicao + 1);
        operacoes.remove(posicao);
        return resultado;
    }

    private float subtracao(int posicao) {
        float resultado = numeros.get(posicao) - numeros.get(posicao + 1);
        numeros.set(posicao, resultado);
        numeros.remove(posicao + 1);
        operacoes.remove(posicao);
        return resultado;
    }

    private float multiplicacao(int posicao) {
        float resultado = numeros.get(posicao) * numeros.get(posicao + 1);
        numeros.set(posicao, resultado);
        numeros.remove(posicao + 1);
        operacoes.remove(posicao);
        return resultado;
    }

    private float divisao(int posicao) {
        float resultado = numeros.get(posicao) / numeros.get(posicao + 1);
        numeros.set(posicao, resultado);
        numeros.remove(posicao + 1);
        operacoes.remove(posicao);
        return resultado;
    }

    public void onClickBotao1 (View v) {
        atualizarNumeroDigitado("1");
        atualizarDisplay("1");
    }

    public void onClickBotao2 (View v) {
        atualizarNumeroDigitado("2");
        atualizarDisplay("2");
    }

    public void onClickBotao3 (View v) {
        atualizarNumeroDigitado("3");
        atualizarDisplay("3");
    }

    public void onClickBotao4 (View v) {
        atualizarNumeroDigitado("4");
        atualizarDisplay("4");
    }

    public void onClickBotao5 (View v) {
        atualizarNumeroDigitado("5");
        atualizarDisplay("5");
    }

    public void onClickBotao6 (View v) {
        atualizarNumeroDigitado("6");
        atualizarDisplay("6");
    }

    public void onClickBotao7 (View v) {
        atualizarNumeroDigitado("7");
        atualizarDisplay("7");
    }

    public void onClickBotao8 (View v) {
        atualizarNumeroDigitado("8");
        atualizarDisplay("8");
    }

    public void onClickBotao9 (View v) {
        atualizarNumeroDigitado("9");
        atualizarDisplay("9");
    }

    public void onClickBotao0 (View v) {
        atualizarNumeroDigitado("0");
        atualizarDisplay("0");
    }

    public void onClickBotaoIgual (View v) {
        if (!numeroDigitado.equals("")) {
            numeros.add(Float.parseFloat(numeroDigitado));
        }
        if (numeros.size() > operacoes.size()) {
            while (!operacoes.isEmpty()) {
                for (int i = 0; i < operacoes.size(); i++) {
                    if (operacoes.get(i).equals("/")) {
                        if (numeros.get(i + 1) == 0) {
                            Toast.makeText(MainActivity.this, "Não foi possível realizar a divisão", Toast.LENGTH_LONG).show();
                            resetAll();
                        } else {
                            float resultado = divisao(i);
                            i = 0;
                            continue;
                        }
                    }
                    if (operacoes.get(i).equals("*")) {
                        float resultado = multiplicacao(i);
                        i = 0;
                        continue;
                    }
                }
                if (!operacoes.isEmpty()) {
                    if (operacoes.get(0).equals("+")) {
                        float resultado = adicao(0);
                        continue;
                    } else if (operacoes.get(0).equals("-")) {
                        float resultado = subtracao(0);
                        continue;
                    }
                }
            }
            if (numeros.get(0) % 1 == 0) {
                display.setText(String.valueOf(Math.round(numeros.get(0))));
            } else {
                display.setText(String.valueOf(numeros.get(0)));
            }
            numeroDigitado = String.valueOf(numeros.get(0));
            numeros.clear();
        } else {
            Toast.makeText(MainActivity.this, "Insira outro número para completar a operação", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickBotaoAdicao (View v) {
        novoCalculo("+");
    }

    public void onClickBotaoSubtrair (View v) {
        novoCalculo("-");
    }

    public void onClickBotaoMultiplicar (View v) {
        novoCalculo("*");
    }

    public void onClickBotaoDivisao(View v) {
        novoCalculo("/");
    }

    public  void resetAll() {
        display.setText("");
        numeroDigitado = "";
        numeros.clear();
        operacoes.clear();
    }

    public void onClickReset(View v) {
        resetAll();
    }
}