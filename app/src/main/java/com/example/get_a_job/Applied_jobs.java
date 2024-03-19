package com.example.get_a_job;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Applied_jobs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Applied_jobs extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recylerView;
    ArrayList<JobDisplayObject> dataSets= new ArrayList<>();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Applied_jobs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Applied_jobs.
     */
    // TODO: Rename and change types and number of parameters
    public static Applied_jobs newInstance(String param1, String param2) {
        Applied_jobs fragment = new Applied_jobs();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recylerView = (RecyclerView) view.findViewById(R.id.recyler_job_applied_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recylerView.setLayoutManager(linearLayoutManager);


        dataSets.add(new JobDisplayObject("Software Developer", "Tech Solutions Inc.", "Toronto, ON", "2024-03-19"));
        dataSets.add(new JobDisplayObject("Data Analyst", "Data Insights Co.", "Vancouver, BC", "2024-03-19"));
        dataSets.add(new JobDisplayObject("Network Engineer", "Connectivity Services Ltd.", "Calgary, AB", "2024-03-19"));
        dataSets.add(new JobDisplayObject("Cybersecurity Specialist", "SecureTech Solutions", "Ottawa, ON", "2024-03-19"));
        dataSets.add(new JobDisplayObject("IT Support Technician", "Resolve IT Services", "Edmonton, AB", "2024-03-19"));
        dataSets.add(new JobDisplayObject("Web Developer", "Digital Innovations Corp.", "Montreal, QC", "2024-03-19"));
        dataSets.add(new JobDisplayObject("Systems Analyst", "TechPro Systems", "Winnipeg, MB", "2024-03-19"));
        dataSets.add(new JobDisplayObject("Database Administrator", "DataWare Corporation", "Halifax, NS", "2024-03-19"));
        dataSets.add(new JobDisplayObject("UI/UX Designer", "DesignTech Solutions", "Quebec City, QC", "2024-03-19"));
        dataSets.add(new JobDisplayObject("Cloud Solutions Architect", "CloudWorks Inc.", "Victoria, BC", "2024-03-19"));

        ArrayAdaptor_JobDisplayObject myAdapter = new ArrayAdaptor_JobDisplayObject(dataSets);
        recylerView.setAdapter(myAdapter);
        Log.d("lifec","onViewCreated");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("test", "Inside Applied job frag");
        // Inflate the layout for this fragment
        Log.d("lifec","onCreateView");
        return inflater.inflate(R.layout.fragment_applied_jobs, container, false);
    }


}