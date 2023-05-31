package com.example.proyfragmentmodal.util;

import android.widget.Filter;

import com.example.proyfragmentmodal.adapter.ListAdapterParticipantes;

import java.util.ArrayList;
import java.util.List;

public class Filtro extends Filter {

    private List<String> originalList;
    private List<String> filteredList;
    private ListAdapterParticipantes adapter;

    public Filtro(List<String> originalList, ListAdapterParticipantes adapter) {
        this.originalList = originalList;
        this.filteredList = new ArrayList<>();
        this.adapter = adapter;
    }
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredList.clear();
        final FilterResults results = new FilterResults();

        if (constraint.length() == 0) {
            filteredList.addAll(originalList);
        } else {
            final String filterPattern = constraint.toString().toLowerCase().trim();

            for (final String item : originalList) {
                if (item.toLowerCase().contains(filterPattern)) {
                    filteredList.add(item);
                }
            }
        }

        results.values = filteredList;
        results.count = filteredList.size();

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.clear();
        adapter.addAll((ArrayList<String>)  results.values);
        adapter.notifyDataSetChanged();
    }

}
