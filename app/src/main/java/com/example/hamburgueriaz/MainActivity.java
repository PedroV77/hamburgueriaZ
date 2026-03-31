package com.example.hamburgueriaz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {


        int quantidade = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        public void somar(View view) {
            quantidade++;
            atualizarQuantidade();
        }

        public void subtrair(View view) {
            if (quantidade > 0) quantidade--;
            atualizarQuantidade();
        }

        public void atualizarQuantidade() {
            TextView txtQuantidade = findViewById(R.id.txtQuantidade);
            txtQuantidade.setText(String.valueOf(quantidade));
        }

        public int calcularPreco(boolean bacon, boolean queijo, boolean onion) {
            int preco = 20;

            if (bacon) preco += 2;
            if (queijo) preco += 2;
            if (onion) preco += 3;

            return preco * quantidade;
        }

        public void enviarPedido(View view) {

            String nome = ((EditText) findViewById(R.id.edtNome)).getText().toString();

            boolean bacon = ((CheckBox) findViewById(R.id.chkBacon)).isChecked();
            boolean queijo = ((CheckBox) findViewById(R.id.chkQueijo)).isChecked();
            boolean onion = ((CheckBox) findViewById(R.id.chkOnion)).isChecked();

            int precoFinal = calcularPreco(bacon, queijo, onion);

            String resumo =
                    "Nome: " + nome + "\n" +
                            "Bacon: " + (bacon ? "Sim" : "Não") + "\n" +
                            "Queijo: " + (queijo ? "Sim" : "Não") + "\n" +
                            "Onion Rings: " + (onion ? "Sim" : "Não") + "\n" +
                            "Quantidade: " + quantidade + "\n" +
                            "Preço: R$ " + precoFinal;

            ((TextView) findViewById(R.id.txtResumo)).setText(resumo);

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Pedido de " + nome);
            intent.putExtra(Intent.EXTRA_TEXT, resumo);



            startActivity(intent);
        }
    }

