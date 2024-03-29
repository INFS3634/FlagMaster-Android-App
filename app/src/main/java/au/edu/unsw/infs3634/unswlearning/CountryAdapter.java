package au.edu.unsw.infs3634.unswlearning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import au.edu.unsw.infs3634.unswlearning.countryAPI.Country;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> implements Filterable {

    private ArrayList<Country> mCountries, mCountryFiltered;
    private RecyclerViewInterface recyclerViewInterface;
    public static final int SORT_METHOD_NAME = 1;
    public static final int SORT_METHOD_REGION = 2;


    //CountryAdapter constructor method
    public CountryAdapter(ArrayList<Country> countries, RecyclerViewInterface rvInterface) {
        this.mCountries = countries;
        this.mCountryFiltered = countries;
        recyclerViewInterface = rvInterface;
    }

    @NonNull
    @Override
    public CountryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_recycler_view_row, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.MyViewHolder holder, int position) {
        //Assign value to each row in RecyclerView based on position of RecyclerView item
        Country country = mCountryFiltered.get(position); // change
        holder.countryName.setText(country.getName());
        holder.countryCapital.setText(country.getCapital());
        holder.countryRegion.setText(country.getRegion());
        /**
         * Add Glide library to display country flag images
         */
        Glide.with(holder.itemView)
                .load("https://countryflagsapi.com/png/" + country.getName() + "/")
                .fitCenter()
                .into(holder.flag);
        holder.itemView.setTag(country.getName());
    }

    @Override
    public int getItemCount() {
        //Return number of items in RecyclerView
        return mCountryFiltered.size();
    }

    //Create filter
    @Override
    public Filter getFilter() {
        return new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                //Convert user input into string
                String query = charSequence.toString();
                //Check user query
                if (query.isEmpty()) {
                    //if empty, show us exact same list as before
                    mCountryFiltered = mCountries;
                } else {
                    //Create a new ArrayList to add filtered countries
                    ArrayList<Country> filteredList = new ArrayList<>();
                    for (Country country : mCountries) {
                        //Filter by name
                        if (country.getName().toLowerCase().contains(query.toLowerCase())) {
                            filteredList.add(country);
                        }
                    }
                    mCountryFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mCountryFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults (CharSequence charSequence, FilterResults filterResults) {
                mCountryFiltered = (ArrayList<Country>)  filterResults.values;
                //change accordingly
                notifyDataSetChanged();
            }
        };
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //Handle view items from country_recycler_view_row.xml layout
        ImageView flag;
        TextView countryName, countryCapital, countryRegion;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            flag = itemView.findViewById(R.id.countryImage);
            countryName = itemView.findViewById(R.id.countryName);
            countryCapital = itemView.findViewById(R.id.countryCapital);
            countryRegion = itemView.findViewById(R.id.countryRegion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        recyclerViewInterface.onItemClick((String) itemView.getTag());
                    }
                }
            });
        }
    }

    //Sort country list by name
    public void sort(final int sortMethod) {
        if (mCountryFiltered.size()> 0) {
            Collections.sort(mCountryFiltered, new Comparator<Country>() {
                @Override
                public int compare(Country country, Country t1) {
                    if(sortMethod == SORT_METHOD_NAME) {
                        return country.getName().toLowerCase().compareTo(t1.getName().toLowerCase());
                    }
                    else if (sortMethod == SORT_METHOD_REGION) {
                        return country.getRegion().toLowerCase().compareTo(t1.getRegion().toLowerCase());
                    }
                    //By default sort by country name
                    return country.getName().compareTo(t1.getName());
                }
            });
        }
        notifyDataSetChanged();
    }

    //Add the supplied data to the adapter
    public void setData(ArrayList<Country> data) {
        mCountries.clear();
        mCountries.addAll(data);
        notifyDataSetChanged();
    }
}
