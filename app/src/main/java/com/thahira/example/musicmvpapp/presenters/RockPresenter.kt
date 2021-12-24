package com.thahira.example.musicmvpapp.presenters

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.thahira.example.musicmvpapp.database.ResultDatabase
import com.thahira.example.musicmvpapp.model.Result
import com.thahira.example.musicmvpapp.rest.MusicApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RockPresenter @Inject constructor(
    var networkApi: MusicApi,
    var connectivityManager: ConnectivityManager,
    var resultDatabase: ResultDatabase
): IRockPresenter{

    private var rockViewContract : IRockView? = null

    private val disposable by lazy{
        CompositeDisposable()
    }

    private var isNetworkAvailable= false

    //assigning the variable coming from the fragment to the local variable
    override fun initPresenter(viewContract: IRockView) {
        rockViewContract = viewContract
    }

    //retrieving to get the data, changing to worker thread, observing on main thread
    //subscribing to get the data
    override fun getRockFromServer() {
        if(isNetworkAvailable)
        {
            val musicDisposable = networkApi
                .retrieveRock()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { rockTracks ->
                       rockViewContract?.rockUpdated(rockTracks.results)},
                    { throwable ->
                    rockViewContract?.onError(throwable)
                    })

            disposable.add(musicDisposable)
        }

    }

    override fun checkNetworkState() {
        isNetworkAvailable = getActiveNetworkCapabilities()?.let{
            it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)&&
                    it.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        }?: false
    }


    override fun destroyPresenter() {
        disposable.clear()
        rockViewContract = null
    }

    private fun getActiveNetworkCapabilities(): NetworkCapabilities? {
        return connectivityManager.activeNetwork.let {
            connectivityManager.getNetworkCapabilities(it)
        }

    }

}

interface  IRockPresenter{
    fun initPresenter(viewContract: IRockView)

    fun getRockFromServer()

    fun checkNetworkState()

    fun destroyPresenter()

}

interface IRockView{
    // to return success response to the view
    fun rockUpdated(rockTracks:List<Result>)

    // to return error response to the view
    fun onError(error:Throwable)


}