package com.example.calculadoradegorjetas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBarPercentualGorjeta;
    private TextInputEditText textInputValorBase;
    private TextView textViewValorGorjeta, textViewValorTotal, textViewPercentualGorjeta;

    private double _valorPercentualGorjeta = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Atribuindo a referência dos componentes utilizados
        seekBarPercentualGorjeta = findViewById(R.id.seekBarPercentualGorjeta);
        textInputValorBase = findViewById(R.id.textInputValorBase);
        textViewPercentualGorjeta = findViewById(R.id.textViewPercentualGorjeta);
        textViewValorGorjeta = findViewById(R.id.textViewValorGorjeta);
        textViewValorTotal = findViewById(R.id.textViewValorTotal);

        inicializarEventoSeekBarPercentualGorjeta();
    }

    private void inicializarEventoSeekBarPercentualGorjeta() {
        seekBarPercentualGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                _valorPercentualGorjeta = progress;

                textViewPercentualGorjeta.setText(Math.round(_valorPercentualGorjeta) + "%");

                String valorBase = textInputValorBase.getText().toString();

                if (valorBase == null || valorBase.equals("")) {
                    Toast.makeText(getApplicationContext(), "Informe o valor base", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Double valorBaseNumerico = Double.parseDouble(valorBase);
                    Double valorGorjeta = calcularValorGorjeta(valorBaseNumerico);
                    Double valorTotal = calcularValorTotal(valorBaseNumerico, valorGorjeta);

                    textViewValorGorjeta.setText("R$ " + Math.round(valorGorjeta));
                    textViewValorTotal.setText("R$ " + Math.round(valorTotal));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private Double calcularValorGorjeta(Double valorBase) {
        Double valorGorjeta = valorBase * (_valorPercentualGorjeta / 100);
        return valorGorjeta;
    }

    private Double calcularValorTotal(Double valorBase, Double valorGorjeta) {
        Double valorTotal = valorBase + valorGorjeta;
        return valorTotal;
    }

}