package com.thahira.example.musicmvpapp.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.thahira.example.musicmvpapp.MusicApplication
import com.thahira.example.musicmvpapp.adapters.PopAdapter
import com.thahira.example.musicmvpapp.adapters.PreviewClick
import com.thahira.example.musicmvpapp.databinding.FragmentPopBinding
import com.thahira.example.musicmvpapp.model.Result
import com.thahira.example.musicmvpapp.presenters.IPopView
import com.thahira.example.musicmvpapp.presenters.PopPresenter

import javax.inject.Inject

class PopFragment : Fragment(),IPopView,PreviewClick {

    @Inject
    lateinit var presenter : PopPresenter

    private lateinit var binding: FragmentPopBinding
    private lateinit var popAdapter : PopAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        // for dagger to inject all the members annotated with @Inject in this fragment
        MusicApplication.musicComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.initPresenter(this)
        presenter.checkNetworkState()

        popAdapter = PopAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPopBinding.inflate(inflater,container,false)
        binding.songRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = popAdapter
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.getPopFromServer()
    }

    override fun previewSong(previewUrl: String?, SongName: String?) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.setDataAndType(Uri.parse(previewUrl),"audio/*")
        Toast.makeText(requireContext(),"Now Playing: $SongName",Toast.LENGTH_LONG).show()
        startActivity(intent)
    }

    override fun popUpdated(popTracks: List<Result>) {
        popAdapter.updatePop(popTracks)
        Toast.makeText(requireContext(),popTracks[0].artistName,Toast.LENGTH_LONG).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroyPresenter()
    }

    override fun onError(error: Throwable) {
        Toast.makeText(requireContext(),error.localizedMessage, Toast.LENGTH_LONG).show()
    }

    companion object {

        @JvmStatic
        fun newInstance()= PopFragment()


    }
}