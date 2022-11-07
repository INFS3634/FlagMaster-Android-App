package au.edu.unsw.infs3634.unswlearning;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> implements Filterable { //@1:09:00

    private ArrayList<Country> mCountry, mResultsFiltered;
    private RecyclerViewInterface recyclerViewInterface;
    public static final int SORT_METHOD_NAME = 1;
    public static final int SORT_METHOD_VALUE = 2;


    public RecycleViewAdapter(ArrayList<Country> country, RecyclerViewInterface rvInterface) {
        mCountry = country;
        mResultsFiltered = country;
        recyclerViewInterface = rvInterface;
    }

    // what is actual list you want to show?

    @NonNull
    @Override
    public RecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row,
                parent,false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        Country country = mResultsFiltered.get(position); // change
        //holder.setTag(country.getFlag());
        holder.nameTv.setText(country.getName());
        holder.regionTv.setText(country.getRegion());
        holder.capitalTv.setText(country.getCapital());
        holder.populationTv.setText(country.getPopulation());
    }

    @Override
    public int getItemCount() {
        return mResultsFiltered.size();
    } //mark

    @Override
    public Filter getFilter() {
        return new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) { //WHAT DOE S THIS MEAN>????
                String query = charSequence.toString(); // pass to string for uh when type into menu bar, it is not string convert so we can handle
                if (query.isEmpty()) {
                    mResultsFiltered = mCountry; // if empty, show us exact same list as before
                }
                else {
                    ArrayList<Country> filteredList = new ArrayList<>(); // we dk what results are, empty to chuck stuff in
                    for (Country country : mResultsFiltered) { //keep checking w original arraylist, for each coin in OG list
                        if (country.getName().toLowerCase().contains(query.toLowerCase())) {  // contains vs .equals
                            filteredList.add(country); //add to new arraylist
                        }
                    }
                    mResultsFiltered = filteredList;// i dk
                }
                FilterResults filterResults = new FilterResults(); //blank paper is filteredlist?
                filterResults.values = mResultsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults (CharSequence charSequence, FilterResults filterResults) {
                mResultsFiltered = (ArrayList<Country>)  filterResults.values; //populate arraylist?
                notifyDataSetChanged(); //change accordingly ^
            }
        };
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView coinImage;
        TextView tvSymbol;
        TextView priceTv;
        TextView changeTv;


        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            coinImage = itemView.findViewById(R.id.coinImage);
            tvSymbol = itemView.findViewById(R.id.tvSymbol);
            priceTv = itemView.findViewById(R.id.priceTv);
            changeTv = itemView.findViewById(R.id.changeTv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        recyclerViewInterface.onItemClick((String) itemView.getTag()); // set tag so it changes every time you click on item,
                        //so it knows where to go????
                    }

                }
            });
        }
    }
    public void sort(final int sortMethod) {
        if (mResultsFiltered.size()> 0) {
            Collections.sort(mResultsFiltered, new Comparator<Country>() {
                @Override
                public int compare(Country country, Country t1) {
                    if(sortMethod ==SORT_METHOD_NAME) {
                        return country.getName().compareTo(t1.getName());
                    }
                    else if (sortMethod == SORT_METHOD_VALUE) {
                        return Double.valueOf(country.getPopulation()).compareTo(Double.valueOf(t1.getPopulation()));
                    }
                    return country.getName().compareTo(t1.getName());
                }
            });
        }
        notifyDataSetChanged();
    }
}
