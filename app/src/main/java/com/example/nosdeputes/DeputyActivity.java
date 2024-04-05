package com.example.nosdeputes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeputyActivity extends AppCompatActivity implements View.OnClickListener { //implements DeputyObserverVote{

    private ImageView imageView, imageViewTwitter, imageViewSexe, imageViewUrlAN;
    private TextView textViewName, textViewCirco, textViewEmail, textViewGroupe,textViewPlaceHemicycle, textViewAdressePerm;
    private ListView listCollabos;
    private Button buttonViewVotes;
    private LinearLayout layoutSeparator, layoutSeparator2, layoutSeparator3;
    private Deputy deputy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deputy);

        textViewName= findViewById(R.id.textViewDeputyName);
        textViewCirco= findViewById(R.id.textViewDeputyCirco);
        textViewGroupe= findViewById(R.id.textViewDeputyGroupe);
        textViewEmail= findViewById(R.id.textViewDeputyEmail);
        textViewPlaceHemicycle=findViewById(R.id.textViewDeputyPlace);
        textViewAdressePerm=findViewById(R.id.textViewDeputyAdresse);

        imageView= findViewById(R.id.imageViewDeputy);
        imageViewTwitter = findViewById(R.id.imageViewTwitter);
        imageViewSexe=findViewById(R.id.imageViewSexe);
        imageViewUrlAN=findViewById(R.id.imageViewUrlAN);

        listCollabos=findViewById(R.id.listViewCollaborator);

        buttonViewVotes=findViewById(R.id.buttonViewVotes);
        buttonViewVotes.setOnClickListener(this);

        layoutSeparator=findViewById(R.id.layoutSeparator);
        layoutSeparator2=findViewById(R.id.layoutSeparator2);
        layoutSeparator3=findViewById(R.id.layoutSeparator3);

        //listViewVote=findViewById(R.id.listViewVote);
        //votes=new ArrayList<>();
        //voteAdapter= new VoteAdapter(votes, this);
        //listViewVote.setAdapter(voteAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.deputy= (Deputy) getIntent().getSerializableExtra("deputy");

        textViewName.setText(this.deputy.getFirstName()+" "+this.deputy.getLastName());
        textViewCirco.setText(this.deputy.getDepartment()+", "+
                this.deputy.getNameCirco()+ " "+ this.deputy.getNumCirco()+
                (this.deputy.getNumCirco()==1? "er": "eme")+" circoncription");
        textViewGroupe.setText(this.deputy.getGroupeParlementaire());
        textViewEmail.setText(this.deputy.getEmail());

        CollaborateurAdapter adapter = new CollaborateurAdapter(this, this.deputy.getCollaborateurs());
        listCollabos.setAdapter(adapter);
        ApiServices.loadDeputyAvatar(this, this.deputy.getNameForAvatar(), imageView);

        imageViewTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String twitterURL =  "https://twitter.com/@" + deputy.getTwitter();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(twitterURL));
                startActivity(intent);
            }
        });
        imageViewUrlAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String anURL =  deputy.getUrlAN();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(anURL));
                startActivity(intent);
            }
        });

        //Affichage sexe Député
        if (this.deputy.getSexe().equals("F")) {
            imageViewSexe.setImageResource(R.drawable.femme);

            //On change la couleur des séparateurs (Rose)
            int color= Color.parseColor("#F53181");
            ColorDrawable pinkColor= new ColorDrawable(color);
            layoutSeparator.setBackground(pinkColor);
            layoutSeparator2.setBackground(pinkColor);
            layoutSeparator3.setBackground(pinkColor);
        } else{
            imageViewSexe.setImageResource(R.drawable.homme);

            //On change la couleur des séparateurs (Bleu)
            int color= Color.parseColor("#01A5E8");
            ColorDrawable blueColor= new ColorDrawable(color);
            layoutSeparator.setBackground(blueColor);
            layoutSeparator2.setBackground(blueColor);
            layoutSeparator3.setBackground(blueColor);
        }
        textViewPlaceHemicycle.setText("Place dans l'hémicycle : "+this.deputy.getPlaceHemicycle());
        textViewAdressePerm.setText("Adresse de la permanence : " + this.deputy.getAdressePerm());




    }

    @Override
    public void onClick(View v) {
        Intent intent= new Intent(DeputyActivity.this, VoteActivity.class);
        intent.putExtra("firstNameDeputy", this.deputy.getFirstName());
        intent.putExtra("lastNameDeputy", this.deputy.getLastName());
        startActivity(intent);
    }
}

