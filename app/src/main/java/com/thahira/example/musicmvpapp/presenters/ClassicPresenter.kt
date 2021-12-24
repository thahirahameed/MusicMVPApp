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

class ClassicPresenter @Inject constructor(
    var networkApi: MusicApi,
    var connectivityManager: ConnectivityManager,
    var resultDatabase: ResultDatabase
): IClassicPresenter{

    private var classicViewContract : IClassicView? = null

    private val disposable by lazy{
        CompositeDisposable()
    }

    private var isNetworkAvailable= false

    //assigning the variable coming from the fragment to the local variable
    override fun initPresenter(viewContract: IClassicView) {
        classicViewContract = viewContract
    }

    //retrieving to get the data, changing to worker thread, observing on main thread
    //subscribing to get the data
    override fun getClassicFromServer() {
        if(isNetworkAvailable)
        {
            val musicDisposable = networkApi
                .retrieveClassic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { classicTracks->
                        classicViewContract?.classicUpdated(classicTracks.results)},
                    { throwable->
                        classicViewContract?.onError(throwable)
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
        classicViewContract = null
    }

    private fun getActiveNetworkCapabilities(): NetworkCapabilities? {
        return connectivityManager.activeNetwork.let {
            connectivityManager.getNetworkCapabilities(it)
        }

    }

}

interface  IClassicPresenter{
    fun initPresenter(viewContract: IClassicView)

    fun getClassicFromServer()

    fun checkNetworkState()

    fun destroyPresenter()

}

interface IClassicView{
    // to return success response to the view
    fun classicUpdated(ClassicTracks:List<Result>)

    // to return error response to the view
    fun onError(error:Throwable)


}