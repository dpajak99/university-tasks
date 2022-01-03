package com.softarea.ppsm_lab_3.dao;

import android.content.Context;
import android.util.Log;

import com.softarea.ppsm_lab_3.R;
import com.softarea.ppsm_lab_3.model.Joke;
import com.softarea.ppsm_lab_3.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class JokesFactory {
  private Context context;
  private List<Joke> jokes;

  public JokesFactory(Context context) {
    this.context = context;
    this.jokes = new ArrayList<>();
  }

  public Joke getRandomJoke() {
    if(jokes.size() == 0) {
      getAllJokes();
    }
    int random = MathUtils.getRandom(jokes.size());
    Joke tmpJoke = jokes.get(random);
    jokes.remove(random);

    return tmpJoke;
  }

  private void getAllJokes() {
    this.jokes.clear();
    String[] arrayOfJokes = context.getResources().getStringArray(R.array.array_of_jokes);
    for( String joke : arrayOfJokes ) {
      jokes.add(new Joke(joke));
    }
  }
}
