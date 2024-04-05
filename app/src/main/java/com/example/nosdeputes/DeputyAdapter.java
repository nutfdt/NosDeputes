package com.example.nosdeputes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DeputyAdapter extends BaseAdapter {
    private ArrayList<Deputy> deputies;
    private Context context;

    public DeputyAdapter(ArrayList<Deputy> deputies, Context context) {
        this.deputies = deputies;
        this.context = context;
    }

    public void setDeputies(ArrayList<Deputy> deputies) {
        this.deputies = deputies;
    }

    @Override
    public int getCount() {
        return deputies.size();
    }

    @Override
    public Object getItem(int i) {
        return deputies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return deputies.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_deputy, viewGroup, false);
        }
        TextView textViewName = view.findViewById(R.id.textViewItemDeputyName);
        textViewName.setText(String.format("%s %s", deputies.get(i).getFirstName(), deputies.get(i).getLastName()));
        TextView textViewCirco = view.findViewById(R.id.textViewItemDeputyCirco);
        textViewCirco.setText(String.format("%s, %s %d%s circonscription",
                deputies.get(i).getDepartment(),
                deputies.get(i).getNameCirco(),
                deputies.get(i).getNumCirco(),
                deputies.get(i).getNumCirco() == 1 ? "er" : "ème"));

        ImageView imageView = view.findViewById(R.id.imageViewItemDeputy);
        ApiServices.loadDeputyAvatar(context, deputies.get(i).getNameForAvatar(), imageView);

        LinearLayout separator=view.findViewById(R.id.layoutSeparatorDeputy);
        if(deputies.get(i).getSexe().equals("F")){
            //On change la couleur du séparateur (Rose)
            int color= Color.parseColor("#F53181");
            ColorDrawable pinkColor= new ColorDrawable(color);
            separator.setBackground(pinkColor);
        }
        else{
            //On change la couleur du séparateur (Bleu)
            int color= Color.parseColor("#01A5E8");
            ColorDrawable blueColor= new ColorDrawable(color);
            separator.setBackground(blueColor);
        }

        return view;
    }
}
