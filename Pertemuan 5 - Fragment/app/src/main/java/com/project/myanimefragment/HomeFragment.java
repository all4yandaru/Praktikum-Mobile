package com.project.myanimefragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.myanimefragment.adapter.GetListAdapter;
import com.project.myanimefragment.dataDummy.AnimeData;
import com.project.myanimefragment.model.Anime;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ArrayList<Anime> listAnime;
    RecyclerView recyclerView;
    GetListAdapter getListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_item);
        listAnime = AnimeData.getListData();

        getListAdapter = new GetListAdapter(listAnime);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(getListAdapter);
    }
}