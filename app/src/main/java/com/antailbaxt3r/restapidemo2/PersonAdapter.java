package com.antailbaxt3r.restapidemo2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antailbaxt3r.restapidemo2.models.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder>{

    private List<Person> list;

    public PersonAdapter(List<Person> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PersonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        holder.populate(list.get(position));
        Log.i("FIRST", list.get(0).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder{

        private TextView name, number, age, id;

        PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            number = itemView.findViewById(R.id.number);
            id = itemView.findViewById(R.id.id);
        }

        void populate(Person person){
            name.setText(person.getName());
            age.setText(person.getAge() + " yrs");
            number.setText("+91 " + person.getNumber());
            id.setText(person.getId());
        }
    }
}
