package com.example.nosdeputes;

import java.util.ArrayList;

public interface DeputyObserverVote {

    void onReceiveVote(ArrayList<Vote> votes);
}