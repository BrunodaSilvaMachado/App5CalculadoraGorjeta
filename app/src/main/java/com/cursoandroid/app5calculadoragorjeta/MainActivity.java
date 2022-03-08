package com.cursoandroid.app5calculadoragorjeta;

import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private EditText editValor;
    private TextView textGorjeta;
    private TextView textTotal;

    final private Locale l = Locale.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor       = findViewById(R.id.editValor);
        TextView textPorcentagem = findViewById(R.id.textPorcentagem);
        textGorjeta     = findViewById(R.id.textGorjeta);
        textTotal       = findViewById(R.id.textTotal);
        SeekBar seekBarGorjeta = findViewById(R.id.seekBarGorjeta);
        
        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textPorcentagem.setText(String.format(l,"%d%%",progress));
                calcular(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void calcular(int porcentagem) {
        String textValor = editValor.getText().toString();
        
        if(textValor.isEmpty()){
            Toast.makeText(getApplicationContext(), "Digite um valor primeiro!", Toast.LENGTH_SHORT).show();
        } else{
            double valorDigitado = Double.parseDouble(textValor);
            double gorjeta = valorDigitado * (porcentagem/100.0);
            double total = gorjeta + valorDigitado;

            textGorjeta.setText(String.format(l,"R$ %.2f",gorjeta));
            textTotal.setText(String.format(l,"R$ %.2f",total));
        }
    }
}