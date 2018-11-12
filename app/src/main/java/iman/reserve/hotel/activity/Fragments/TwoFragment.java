package iman.reserve.hotel.activity.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import iman.reserve.hotel.R;
import iman.reserve.hotel.adapter.GetCommentAdapter;
import iman.reserve.hotel.model.CommentList;
import iman.reserve.hotel.model.Comment;
import iman.reserve.hotel.model.Message;
import iman.reserve.hotel.network.GetCommentDataService;
import iman.reserve.hotel.network.RetrofitInstance;
import iman.reserve.hotel.network.SubmitCommentService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class TwoFragment extends Fragment{

    private RecyclerView recyclerView;
    private GetCommentAdapter adapter;
    private View view;
    private SharedPreferences prefs;
    private String username,selhot;
    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_two, container, false);
        prefs = getContext().getSharedPreferences("UserData", MODE_PRIVATE);
        username = prefs.getString("username","");
        selhot = prefs.getString("selectedhotel","");

        view.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        listComments();
        submitComment();

        return view;
    }


    private void listComments(){
        GetCommentDataService service = RetrofitInstance.getRetrofitInstance().create(GetCommentDataService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<CommentList> call = service.getCommentData(selhot);

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<CommentList>() {
            @Override
            public void onResponse(Call<CommentList> call, Response<CommentList> response) {
                generateCommentList(response.body().getCommentArrayList());
            }

            @Override
            public void onFailure(Call<CommentList> call, Throwable t) {
                Toast.makeText(getContext(), R.string.no_internet, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateCommentList(ArrayList<Comment> cmntDataList) {
        float userating = 0;

        for(int i=0 ; i < cmntDataList.size() ; i++){
            userating += cmntDataList.get(i).getRating();
        }
        userating = userating/cmntDataList.size();
        TextView txtrating = view.findViewById(R.id.userrating);
        String txt = String.format("%.2f", userating) + "از 5";
        txtrating.setText(txt);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_comments);
        adapter = new GetCommentAdapter(cmntDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setAdapter(adapter);
    }

    private void submitComment(){
        Button commentbut = view.findViewById(R.id.commentbut);
        final EditText txtComment = view.findViewById(R.id.commentText);
        final RatingBar ratingBar = view.findViewById(R.id.ratingBar);

        commentbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = txtComment.getText().toString();
                float rating = ratingBar.getRating();

                if (rating < 0.5) {
                    Snackbar.make(view,"حداقل امتیاز ممکن 0.5 می باشد",Snackbar.LENGTH_LONG).show();
                    //Toast.makeText(getContext(), "حداقل امتیاز ممکن 0.5 می باشد", Toast.LENGTH_SHORT).show();
                } else {
                    SubmitCommentService service = RetrofitInstance.getRetrofitInstance().create(SubmitCommentService.class);

                    Call<Message> call = service.getSubmitData(selhot, username, comment, rating);
                    Log.wtf("URL Called", call.request().url() + "");
                    call.enqueue(new Callback<Message>() {
                        @Override
                        public void onResponse(Call<Message> call, Response<Message> response) {
                            listComments();
                            String result = response.body().getMessage();
                            Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Message> call, Throwable t) {
                            Toast.makeText(getContext(), R.string.no_internet, Toast.LENGTH_SHORT).show();
                        }
                    });

                    txtComment.setText("");
                    ratingBar.setRating(0);
                    listComments();
                }
            }
        });
    }
}