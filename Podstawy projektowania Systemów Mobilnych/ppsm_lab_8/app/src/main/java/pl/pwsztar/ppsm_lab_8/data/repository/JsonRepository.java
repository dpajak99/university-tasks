package pl.pwsztar.ppsm_lab_8.data.repository;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonRepository {
  private RepoService repoService;
  private static JsonRepository jsonRepository;

  private JsonRepository() {
    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(RepoService.HTTPS_API_MPK_URL)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .build();

    repoService = retrofit.create(RepoService.class);
  }

  public synchronized static JsonRepository getInstance() {
    if (jsonRepository == null) {
      jsonRepository = new JsonRepository();
    }
    return jsonRepository;
  }

  public RepoService getRepoService() {
    return repoService;
  }
}
