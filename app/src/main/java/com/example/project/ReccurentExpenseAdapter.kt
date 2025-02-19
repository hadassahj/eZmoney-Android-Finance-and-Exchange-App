package com.example.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecurrentExpenseAdapter( // clasa care se ocupa de afisarea elementelor din lista de cheltuieli recurente
    private val expenses: MutableList<RecurrentExpense>, //lista cu obiecte de tip RecurrentExpense
    private val onDelete: (RecurrentExpense) -> Unit // lambda care se apeleaza cand se sterge un element din lista, nu returneaza nimic
) : RecyclerView.Adapter<RecurrentExpenseAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) { // clasa care reprezinta un element din lista
        val nameTextView: TextView = view.findViewById(R.id.expense_name)
        val valueTextView: TextView = view.findViewById(R.id.expense_value)
        val deleteButton: Button = view.findViewById(R.id.delete_button) // butonul care sterge elementul
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {  // creeaza un ViewHolder pentru fiecare element din lista
        val view = LayoutInflater.from(parent.context) //pt fiecare item avem un layout
            .inflate(R.layout.item_recurrent_expense, parent, false) // se foloseste layout-ul item_recurrent_expense
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { // se apeleaza pentru fiecare element din lista
        val expense = expenses[position] // se ia elementul de pe pozitia "position" din lista "expenses"
        holder.nameTextView.text = expense.name // se seteaza numele cheltuielii
        holder.valueTextView.text = expense.value.toString() // se seteaza valoarea cheltuielii
        holder.deleteButton.setOnClickListener {
            onDelete(expense)   // se apeleaza functia onDelete care sterge elementul din lista
            expenses.removeAt(position) // se sterge elementul din lista
            notifyItemRemoved(position) // se notifica adapterul ca s-a sters un element
            notifyItemRangeChanged(position, expenses.size) // se notifica adapterul ca s-a schimbat numarul de elemente
        }
    }

    override fun getItemCount() = expenses.size // functie care returneaza numarul de elemente din lista
}