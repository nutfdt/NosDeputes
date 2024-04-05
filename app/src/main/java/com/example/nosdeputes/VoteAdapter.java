package com.example.nosdeputes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class VoteAdapter extends BaseAdapter {

    private ArrayList<Vote> votes;
    private Context context;
    public VoteAdapter(ArrayList<Vote> votes, Context context){
        this.votes=votes;
        this.context=context;
    }
    public void setVote(ArrayList<Vote> votes){
        this.votes=votes;
    }
    @Override
    public int getCount() {
        return this.votes.size();
    }

    @Override
    public Object getItem(int position) {
        return this.votes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_vote, parent, false);
        }
        TextView titre=convertView.findViewById(R.id.textViewItemVoteTitre);
        TextView votePour=convertView.findViewById(R.id.textViewItemVotePour);
        TextView voteContre=convertView.findViewById(R.id.textViewItemVoteContre);
        TextView voteAbstention=convertView.findViewById(R.id.textViewItemVoteAbstention);
        TextView resultat=convertView.findViewById(R.id.textViewItemVoteResultat);
        TextView date=convertView.findViewById(R.id.textViewItemVoteDate);
        TextView positionVote=convertView.findViewById(R.id.textViewItemVotePosition);

        ImageView image=convertView.findViewById(R.id.imageViewVote);

        //Mettre majuscule première lettre du titre
        String titreText = votes.get(position).getTitre();
        titreText = titreText.substring(0, 1).toUpperCase() + titreText.substring(1);
        titre.setText(titreText);

        votePour.setText("Pour : "+votes.get(position).getNbPours());
        voteContre.setText("Contre : "+votes.get(position).getNbContres());
        voteAbstention.setText("Abstention : "+votes.get(position).getNbAbstentions());
        resultat.setText("Résultat : "+votes.get(position).getSort());
        date.setText(votes.get(position).getDate());

        //Mettre majuscule première lettre de la position du Député
        String positionVoteText = votes.get(position).getPosition();
        positionVoteText = positionVoteText.substring(0, 1).toUpperCase() + positionVoteText.substring(1);
        positionVote.setText(positionVoteText);

        //Changement image en fonction de la position du député
        if (votes.get(position).getPosition().equals("abstention")) {
            image.setImageResource(R.drawable.abstention);
        }
        else if(votes.get(position).getPosition().equals("pour")){
            image.setImageResource(R.drawable.pour);
        }
        else{
            image.setImageResource(R.drawable.contre);
        }
        //Changement couleur texte sur le résultat final du vote
        if(votes.get(position).getSort().equals("adopté")){
            resultat.setTextColor(Color.GREEN);
        }
        else{
            resultat.setTextColor(Color.RED);
        }
        return convertView;
    }
}
