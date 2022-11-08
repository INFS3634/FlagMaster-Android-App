package au.edu.unsw.infs3634.unswlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<Country> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView countryName;
        TextView capital;
        ImageView imageViewFlag;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.countryName = (TextView) itemView.findViewById(R.id.countryName);
            this.capital = (TextView) itemView.findViewById(R.id.capital);
            this.imageViewFlag = (ImageView) itemView.findViewById(R.id.countryImage);
        }
    }

    public CustomAdapter(ArrayList<Country> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_row, parent, false);

        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView countryName = holder.countryName;
        TextView capital = holder.capital;
        ImageView imageView = holder.imageViewFlag;

        countryName.setText(dataSet.get(listPosition).getName());
        capital.setText(dataSet.get(listPosition).getCapital());
        imageView.setImageResource(dataSet.get(listPosition).getFlag());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
