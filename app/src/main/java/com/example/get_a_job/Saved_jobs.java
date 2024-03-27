package com.example.get_a_job;

import android.database.Cursor;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Saved_jobs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Saved_jobs extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recylerView;
    ArrayList<JobDisplayObject> dataSets= new ArrayList<>();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Saved_jobs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Saved_jobs.
     */
    // TODO: Rename and change types and number of parameters
    public static Saved_jobs newInstance(String param1, String param2) {
        Saved_jobs fragment = new Saved_jobs();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    RecyclerView recyclerView;
    String userEmail;
    ArrayAdaptor_Saved_JobDisplayObject myAdapter;
    TextView tv_nodata_display;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recylerView = (RecyclerView) view.findViewById(R.id.recyler_job_saved_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recylerView.setLayoutManager(linearLayoutManager);
        tv_nodata_display = (TextView) view.findViewById(R.id.tv_nosaved_job_display) ;
/*
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
*/
        myAdapter = new ArrayAdaptor_Saved_JobDisplayObject(dataSets,userEmail,this);
        recylerView.setAdapter(myAdapter);
        fetchDataFromDB();
        Log.d("test","onViewCreated Saved_jobs");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        if (getArguments() != null) {
            userEmail = getArguments().getString("user_email");
            Log.d("test", "onCreate: getting useremail ="+userEmail);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("test", "onCreateview: getting useremail ="+userEmail);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_jobs, container, false);
        recyclerView = view.findViewById(R.id.recyler_job_saved_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter = new ArrayAdaptor_Saved_JobDisplayObject(dataSets,userEmail,this);
        recyclerView.setAdapter(myAdapter);
        return view;

    }
    public void refreshDataFrom(){
        String userEmail = getArguments().getString("user_email");
        if (userEmail != null) {
            Log.d("test", "fetchDataFromDB: inside 1 if ");
            DBHelper dbHelper = new DBHelper(getContext(), "test_db", null, 1);
            Cursor cursor = dbHelper.getSavedJobs2(userEmail);

            if (cursor != null && cursor.moveToFirst()) {
                Log.d("test", "fetchDataFromDB: inside 2 if ");
                Log.d("testñ","muñaño");
                dataSets.clear();
                do {
                    // job_id,title,company,location,salary,date,description
                    String title = cursor.getString(0);
                    String company = cursor.getString(1);
                    String location = cursor.getString(2);
                    String date = cursor.getString(3);
                    String id = cursor.getString(5);
                    String is_applied = cursor.getString(6);
                    Log.d("test", "adding title "+title);
                    Log.d("test", "adding comp "+company);
                    Log.d("test", "adding loc "+location);
                    Log.d("test", "adding date "+date);

                    JobDisplayObject job = new JobDisplayObject(title, company, location, date,id,is_applied);
                    dataSets.add(job);
                    Log.d("test", "adding data "+job);
                } while (cursor.moveToNext());

                cursor.close();
                myAdapter.notifyDataSetChanged();
            } else{
                dataSets.clear();
                myAdapter.notifyDataSetChanged();
                tv_nodata_display.setText("You do not have any saved jobs yet :/");
                Log.d("test", "no data found: ");


            }
        }
        else {


            Log.d("test", "fetchDataFromDB: useremail is null ");
        }
    }
    public void fetchDataFromDB() {
        Log.d("test", "fetchDataFromDB: ");
        String userEmail = getArguments().getString("user_email");
        //dataSets = new ArrayList<>();

        if (userEmail != null) {
            Log.d("test", "fetchDataFromDB: inside 1 if ");
            DBHelper dbHelper = new DBHelper(getContext(), "test_db", null, 1);
            Cursor cursor = dbHelper.getSavedJobs2(userEmail);

            if (cursor != null && cursor.moveToFirst()) {
                Log.d("test", "fetchDataFromDB: inside 2 if ");
                do {
                    // job_id,title,company,location,salary,date,description
                    String title = cursor.getString(0);
                    String company = cursor.getString(1);
                    String location = cursor.getString(2);
                    String date = cursor.getString(3);
                    String id = cursor.getString(5);
                    String is_applied = cursor.getString(6);
                    Log.d("test", "adding title "+title);
                    Log.d("test", "adding comp "+company);
                    Log.d("test", "adding loc "+location);
                    Log.d("test", "adding date "+date);

                    JobDisplayObject job = new JobDisplayObject(title, company, location, date,id,is_applied);
                    dataSets.add(job);
                    Log.d("test", "adding data "+job);
                } while (cursor.moveToNext());

                cursor.close();
                myAdapter.notifyDataSetChanged();
            } else{

                tv_nodata_display.setText("You do not have any saved jobs yet :/");
                Log.d("test", "no data found: ");
                myAdapter.notifyDataSetChanged();


            }
        }
        else {


            Log.d("test", "fetchDataFromDB: useremail is null ");
        }
    }
    public static Saved_jobs newInstance(String userEmail) {

        Saved_jobs fragment = new Saved_jobs();
        Bundle args = new Bundle();
        args.putString("user_email", userEmail);
        fragment.setArguments(args);
        return fragment;
    }
}