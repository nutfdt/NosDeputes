package com.example.nosdeputes;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class VoteActivity extends AppCompatActivity implements DeputyObserverVote{
    private String firstName;
    private String lastName;
    private TextView textViewDeputy;
    private ListView listViewVotes;
    private ArrayList<Vote> votes;
    private VoteAdapter voteAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        textViewDeputy=findViewById(R.id.textViewVoteDeputy);

        listViewVotes=findViewById(R.id.listViewVotes);
        listViewVotes=findViewById(R.id.listViewVotes);
        votes=new ArrayList<>();
        voteAdapter= new VoteAdapter(votes, this);
        listViewVotes.setAdapter(voteAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        firstName= (String) getIntent().getSerializableExtra("firstNameDeputy");
        lastName= (String) getIntent().getSerializableExtra("lastNameDeputy");
        textViewDeputy.setText(firstName+" "+lastName);

        ApiServices.searchRequestVotes(this, firstName + "-" + lastName, this);
    }

    @Override
    public void onReceiveVote(ArrayList<Vote> votes) {
        voteAdapter.setVote(votes);
        voteAdapter.notifyDataSetChanged();
    }
}
