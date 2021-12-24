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
import com.thahira.example.musicmvpapp.adapters.ClassicAdapter
import com.thahira.example.musicmvpapp.adapters.PreviewClick
import com.thahira.example.musicmvpapp.databinding.FragmentClassicBinding
import com.thahira.example.musicmvpapp.model.Result
import com.thahira.example.musicmvpapp.presenters.ClassicPresenter
import com.thahira.example.musicmvpapp.presenters.IClassicView

import javax.inject.Inject


class ClassicFragment: Fragment(), IClassicView, PreviewClick {

    @Inject lateinit var presenter : ClassicPresenter

    private lateinit var binding: FragmentClassicBinding
    private var classicAdapter = ClassicAdapter(this)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // for dagger to inject all the members annotated with @Inject in this fragment
        MusicApplication.musicComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.initPresenter(this)
        presenter.checkNetworkState()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          binding= FragmentClassicBinding.inflate(inflater,container,false)
            binding.songRecycler.apply{
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                adapter = classicAdapter
            }

        return binding.root
        // Inflate the layout for this fragment
    }

    override fun onResume() {
        super.onResume()
        presenter.getClassicFromServer()
    }

    override fun previewSong(previewUrl: String?, SongName: String?) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.setDataAndType(Uri.parse(previewUrl),"audio/*")
        Toast.makeText(requireContext(),"Now Playing: $SongName",Toast.LENGTH_LONG).show()
        startActivity(
            intent)
    }

    override fun classicUpdated(ClassicTracks: List<Result>) {
        classicAdapter.updateClassic(ClassicTracks)
        Toast.makeText(requireContext(), ClassicTracks[0].artistName, Toast.LENGTH_LONG).show()
    }

    override fun onError(error: Throwable) {
        Toast.makeText(requireContext(),error.localizedMessage, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroyPresenter()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClassicFragment()
    }
}