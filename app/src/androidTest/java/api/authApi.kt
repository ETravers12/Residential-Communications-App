package api

object ApiForAuthentication{

    //OkhttpClient for building http request url
    private val authClient = OkHttpClient().newBuilder()
        .build()

    fun retrofit() : Retrofit = Retrofit.Builder()
        .client(authClient)
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    val authApi : AuthApi = retrofit().create(AuthApi::class.java)

}