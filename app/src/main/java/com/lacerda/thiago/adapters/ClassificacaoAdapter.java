package com.lacerda.thiago.adapters;

import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.lacerda.thiago.apostak.JogosUsuarioActivity;
import com.lacerda.thiago.apostak.R;
import com.lacerda.thiago.entities.Classificacao;

public class ClassificacaoAdapter extends ArrayAdapter<Classificacao> {
    private final Context context;
    private final ArrayList<Classificacao> classificacao;

    private TextView tvClassificacao;
    private TextView tvNome;
    private TextView tvPontos;

    public ClassificacaoAdapter(Context context, ArrayList<Classificacao> classificacao) {
        super(context, R.layout.classificacao_item, classificacao);
        this.context = context;
        this.classificacao = classificacao;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 2. Get rowView from inflater
        View rowView = null;
        rowView = inflater.inflate(R.layout.classificacao_item, parent, false);

        tvClassificacao = (TextView)rowView.findViewById(R.id.classificacao);
        tvNome = (TextView)rowView.findViewById(R.id.nome_classificado);
        tvPontos = (TextView)rowView.findViewById(R.id.pontos);

        final Classificacao objClassificacao = (Classificacao)classificacao.get(position);
        tvClassificacao.setText(String.valueOf(objClassificacao.getClassificacao()));
        tvNome.setText(objClassificacao.getNome());
        tvPontos.setText(String.valueOf(objClassificacao.getPontos()) + " PTS");

        // Set OnClickListener
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(context, JogosUsuarioActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("RODADA_ID", objClassificacao.getRodadaId());
                    intent.putExtra("USUARIO_ID", objClassificacao.getUsuarioId());
                    intent.putExtra("USUARIO_NOME", objClassificacao.getNome());
                    context.startActivity(intent);
                } catch (Exception e) {
                    Log.e("onClick Item Classifica????o", e.getMessage());
                }
            }
        });

        return rowView;
    }
}