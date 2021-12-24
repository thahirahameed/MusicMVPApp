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
import com.thahira.example.musicmvpapp.adapters.PreviewClick
import com.thahira.example.musicmvpapp.adapters.RockAdapter
import com.thahira.example.musicmvpapp.databinding.FragmentRockBinding
import com.thahira.example.musicmvpapp.model.Result
import com.thahira.example.musicmvpapp.presenters.IRockView
import com.thahira.example.musicmvpapp.presenters.RockPresenter
import javax.inject.Inject

class RockFragment : Fragment(), IRockView, PreviewClick {

    // Injecting our presenter
    @Inject lateinit var presenter : RockPresenter

    private lateinit var binding : FragmentRockBinding
    private lateinit var rockAdapter : RockAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // for dagger to inject all the members annotated with @Inject in this fragment
        MusicApplication.musicComponent.inject(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.initPresenter(this)
        presenter.checkNetworkState()

        rockAdapter = RockAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRockBinding.inflate(inflater,container,false)
        binding.songRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rockAdapter
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.getRockFromServer()
    }

    override fun previewSong(previewUrl: String?, SongName: String?) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.setDataAndType(Uri.parse(previewUrl),"audio/*")
        Toast.makeText(requireContext(),"Now Playing: $SongName",Toast.LENGTH_LONG).show()
        startActivity(intent)
    }

    override fun rockUpdated(rockTracks: List<Result>) {
        rockAdapter.updateRock(rockTracks)
        Toast.makeText(requireContext(),rockTracks[0].artistName,Toast.LENGTH_LONG).show()
    }


    override fun onError(error: Throwable) {
        Toast.makeText(requireContext(),error.localizedMessage,Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroyPresenter()
    }


    companion object {

        @JvmStatic
        fun newInstance() = RockFragment()
    }
}