package com.example.app_java;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class GroupTask extends AppCompatActivity {
    private List<Task> deletedTasksList = new ArrayList<>();
    private TaskAdapter deletedTasksAdapter;

    private List<Task> todoList = new ArrayList<>();
    private List<Task> doingList = new ArrayList<>();
    private List<Task> doneList = new ArrayList<>();
    private TaskAdapter todoAdapter;
    private TaskAdapter doingAdapter;
    private TaskAdapter doneAdapter;
    private BottomNavigationView bottomNavigationView; // Declare the variable
    private RecyclerView recyclerViewTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_task);
        recyclerViewTodo = findViewById(R.id.recycler_todo);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN, // Déplacement autorisé en haut et en bas
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) { // Déplacement autorisé vers la gauche (suppression) et vers la droite (déplacement)
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // Gérer le déplacement des éléments (non nécessaire pour le glissement vers la gauche/droite)
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Task task = todoAdapter.getItem(position); // Déplacer la déclaration de la variable task ici

                switch (direction) {
                    case ItemTouchHelper.LEFT: // Glissement vers la gauche (suppression)
                        showDeleteTaskDialog(task, position); // Afficher le dialogue de confirmation de suppression
                        break;
                    case ItemTouchHelper.RIGHT: // Glissement vers la droite (déplacement)
                        showCategoryDialog(task); // Afficher le dialogue de sélection de la catégorie
                        break;
                }
            }

        });
        itemTouchHelper.attachToRecyclerView(recyclerViewTodo); // Attacher l'ItemTouchHelper au RecyclerView "To Do"
        Button showAllTasksButton = findViewById(R.id.show_all_tasks_button);
        showAllTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllTasks();
            }
        });


        bottomNavigationView = findViewById(R.id.bottomNavigationView); // Initialize the variable
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.setting:
                        Toast.makeText(GroupTask.this, "tache de l'equipe", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.home:
                        Intent settingIntent = new Intent(GroupTask.this, SingleActivity.class);
                        startActivity(settingIntent);
                        Toast.makeText(GroupTask.this, " Liste des taches ", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.out:
                        Intent outIntent = new Intent(GroupTask.this, HomeActivity.class);
                        startActivity(outIntent);
                        Toast.makeText(GroupTask.this, "Log Out", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.team:
                        Intent outIntents = new Intent(GroupTask.this, TeamActivity.class);
                        startActivity(outIntents);
                        Toast.makeText(GroupTask.this, "Équipe", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        RecyclerView recyclerViewTodo = findViewById(R.id.recycler_todo);
        RecyclerView recyclerViewDoing = findViewById(R.id.recycler_doing);
        RecyclerView recyclerViewDone = findViewById(R.id.recycler_done);

        todoAdapter = new TaskAdapter(todoList);
        doingAdapter = new TaskAdapter(doingList);
        doneAdapter = new TaskAdapter(doneList);

        recyclerViewTodo.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDoing.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDone.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTodo.addItemDecoration(createDividerItemDecoration());
        recyclerViewDoing.addItemDecoration(createDividerItemDecoration());
        recyclerViewDone.addItemDecoration(createDividerItemDecoration());


        recyclerViewTodo.setAdapter(todoAdapter);
        recyclerViewDoing.setAdapter(doingAdapter);
        recyclerViewDone.setAdapter(doneAdapter);



        // Set click listeners for each RecyclerView item to edit or delete tasks
        todoAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle click on To-Do item to edit or delete task
                Task task = todoList.get(position);
                // You can open a dialog here to edit task details
                // For now, we'll just remove the task
                //todoList.remove(position);
                //todoAdapter.notifyItemRemoved(position);
                updateTotalTaskCount();
                showEditTaskDialog(task);
            }
        });doingAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                doingList.remove(position);
                doingAdapter.notifyItemRemoved(position);
                updateTotalTaskCount();
            }
        });

        doneAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                doneList.remove(position);
                doneAdapter.notifyItemRemoved(position);
                updateTotalTaskCount();
            }
        });

        // Similar listeners can be set for doing and done lists

        Button addTodoButton = findViewById(R.id.add_todo);
        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddTaskDialog();
            }
        });
        loadTasks();


        itemTouchHelper.attachToRecyclerView(recyclerViewTodo);
    }
    private void loadTasks() {
        // Mock data for demonstration
        todoList.add(new Task(1, "Task 1", "Creation du profile", "High", "ASMAE AB", "2024-05-20", "2024-05-25", "To-Do"));
        todoList.add(new Task(2, "Task 2", "Description 2", "Medium", "AZIZA AT", "2024-05-21", "2024-05-26", "To-Do"));
        doingList.add(new Task(3, "Task 3", "Description 3", "Low", "Aya EL", "2024-05-22", "2024-05-27", "Doing"));
        doneList.add(new Task(4, "Task 4", "Description 4", "High", "Douae EL, aziza AT", "2024-05-23", "2024-05-28", "Done"));

        // Update the initials for each user in the tasks
        updateInitialsInTasks(todoList);
        updateInitialsInTasks(doingList);
        updateInitialsInTasks(doneList);

        todoAdapter.notifyDataSetChanged();
        doingAdapter.notifyDataSetChanged();
        doneAdapter.notifyDataSetChanged();
        updateTotalTaskCount();
    }

    private void updateInitialsInTasks(List<Task> taskList) {
        for (Task task : taskList) {
            String[] users = task.getAssignedUser().split(",\\s*");
            StringBuilder initialsBuilder = new StringBuilder();
            for (String user : users) {
                String[] nameParts = user.split("\\s+");
                if (nameParts.length >= 2) {
                    initialsBuilder.append(nameParts[0].charAt(0));
                    initialsBuilder.append(nameParts[1].charAt(0));
                }
                initialsBuilder.append(", ");
            }
            if (initialsBuilder.length() > 0) {
                initialsBuilder.setLength(initialsBuilder.length() - 2); // Remove the trailing comma and space
            }
            task.setAssignedUser(initialsBuilder.toString());
        }
    }

    private RecyclerView.ItemDecoration createDividerItemDecoration() {
        return new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
    }

    private void showCategoryDialog(final Task newTask) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Category");
        final String[] categories = {"To Do", "Doing", "Done"};
        builder.setItems(categories, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        todoList.add(newTask);
                        todoAdapter.notifyItemInserted(todoList.size() - 1);
                        break;
                    case 1:
                        doingList.add(newTask);
                        doingAdapter.notifyItemInserted(doingList.size() - 1);
                        break;
                    case 2:
                        doneList.add(newTask);
                        doneAdapter.notifyItemInserted(doneList.size() - 1);
                        break;
                }
                updateTotalTaskCount();
            }
        });
        builder.show();
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Task");

        // Set up the input layout
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_task, null);
        final EditText titleInput = dialogView.findViewById(R.id.edit_title);
        final EditText descriptionInput = dialogView.findViewById(R.id.edit_description);
        final Spinner prioritySpinner = dialogView.findViewById(R.id.spinner_priority);
        final TextView assignedUsersTextView = dialogView.findViewById(R.id.text_assigned_users);
        final EditText creationDateInput = dialogView.findViewById(R.id.edit_creation_date);
        final EditText dueDateInput = dialogView.findViewById(R.id.edit_due_date);
        final EditText statusInput = dialogView.findViewById(R.id.edit_status);

        // Populate the priority spinner
        List<String> priorityList = new ArrayList<>();
        priorityList.add("High");
        priorityList.add("Medium");
        priorityList.add("Low");
        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, priorityList);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(priorityAdapter);

        assignedUsersTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUserSelectionDialog(assignedUsersTextView);
            }
        });

        builder.setView(dialogView);

        creationDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(creationDateInput);
            }
        });

        dueDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(dueDateInput);
            }
        });

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String taskTitle = titleInput.getText().toString().trim();
                String taskDescription = descriptionInput.getText().toString().trim();
                String taskPriority = prioritySpinner.getSelectedItem().toString();
                String taskAssignedUsers = assignedUsersTextView.getText().toString();
                String taskCreationDate = creationDateInput.getText().toString().trim();
                String taskDueDate = dueDateInput.getText().toString().trim();
                String taskStatus = statusInput.getText().toString().trim();

                Task newTask = new Task(todoList.size() + 1, taskTitle, taskDescription, taskPriority, taskAssignedUsers, taskCreationDate, taskDueDate, taskStatus);
                showCategoryDialog(newTask);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void showUserSelectionDialog(final TextView selectedUsersTextView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Users");

        final List<String> userList = getUserList(); // Liste des utilisateurs
        final boolean[] checkedItems = new boolean[userList.size()];
        final List<String> selectedUsers = new ArrayList<>();

        builder.setMultiChoiceItems(userList.toArray(new String[0]), checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    selectedUsers.add(userList.get(which));
                } else {
                    selectedUsers.remove(userList.get(which));
                }
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedUsersTextView.setText(String.join(", ", selectedUsers));
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private List<String> getUserList() {
        List<String> users = new ArrayList<>();
        users.add("Asmae AB ");
        users.add("Aziza AT");
        users.add("Douae EL");
        users.add("Aya EL");
        return users;
    }
    private void showDatePickerDialog(final EditText dateInput) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Set the selected date in the EditText
                String selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
                dateInput.setText(selectedDate);
            }
        });
        datePickerDialog.show();
    }

    private void showEditTaskDialog(final Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Task");

        // Set up the input layout
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_task, null);
        final EditText titleInput = dialogView.findViewById(R.id.edit_title);
        final EditText descriptionInput = dialogView.findViewById(R.id.edit_description);
        final Spinner prioritySpinner = dialogView.findViewById(R.id.spinner_priority);
        final TextView assignedUsersTextView = dialogView.findViewById(R.id.text_assigned_users);
        final EditText creationDateInput = dialogView.findViewById(R.id.edit_creation_date);
        final EditText dueDateInput = dialogView.findViewById(R.id.edit_due_date);
        final EditText statusInput = dialogView.findViewById(R.id.edit_status);

        // Populate the priority spinner
        List<String> priorityList = new ArrayList<>();
        priorityList.add("High");
        priorityList.add("Medium");
        priorityList.add("Low");
        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, priorityList);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(priorityAdapter);

        // Set the fields with the task details
        titleInput.setText(task.getTitle());
        descriptionInput.setText(task.getDescription());
        prioritySpinner.setSelection(priorityList.indexOf(task.getPriority()));
        assignedUsersTextView.setText(task.getAssignedUser());
        creationDateInput.setText(task.getCreationDate());
        dueDateInput.setText(task.getDueDate());
        statusInput.setText(task.getStatus());

        assignedUsersTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUserSelectionDialog(assignedUsersTextView);
            }
        });

        builder.setView(dialogView);

        creationDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(creationDateInput);
            }
        });

        dueDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(dueDateInput);
            }
        });

        // Set up the buttons
        builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Update the task with the new values
                task.setTitle(titleInput.getText().toString().trim());
                task.setDescription(descriptionInput.getText().toString().trim());
                task.setPriority(prioritySpinner.getSelectedItem().toString());
                task.setAssignedUser(assignedUsersTextView.getText().toString());
                task.setCreationDate(creationDateInput.getText().toString().trim());
                task.setDueDate(dueDateInput.getText().toString().trim());
                task.setStatus(statusInput.getText().toString().trim());

                // Notify the adapter of the change
                int position = todoList.indexOf(task);
                if (position != -1) {
                    todoList.set(position, task); // Update the task in the list
                    todoAdapter.notifyItemChanged(position); // Notify the adapter of the changed item
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    private void showDeleteTaskDialog(final Task task, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SUPPRIMER LA TACHE ");
        builder.setMessage("VOULEZ VOUS SUPPRIMER ?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Supprimer la tâche de la liste et l'ajouter dans la liste des tâches supprimées
                deletedTasksList.add(task);
                if (deletedTasksAdapter != null) {
                    deletedTasksAdapter.notifyItemInserted(deletedTasksList.size() - 1);
                }
                todoList.remove(task);
                todoAdapter.notifyItemRemoved(position);
                updateTotalTaskCount();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Annuler l'action de suppression
                todoAdapter.notifyItemChanged(position);
            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                // Si le dialogue est annulé, annuler l'action de suppression
                todoAdapter.notifyItemChanged(position);
            }
        });


        builder.show();
    }


    private void showAllTasks() {
        // Create a list of all tasks
        List<Task> allTasks = new ArrayList<>(todoList);
        allTasks.addAll(doingList);
        allTasks.addAll(doneList);

        // Create a dialog to display the tasks
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Liste des tâches");

        // Create a string representation of all tasks
        StringBuilder tasksStringBuilder = new StringBuilder();
        for (Task task : allTasks) {
            tasksStringBuilder.append("Titre: ").append(task.getTitle()).append("\n")
                    .append("Description: ").append(task.getDescription()).append("\n")
                    .append("Priorité: ").append(task.getPriority()).append("\n")
                    .append("Utilisateur(s) assigné(s): ").append(task.getAssignedUser()).append("\n")
                    .append("Date de création: ").append(task.getCreationDate()).append("\n")
                    .append("Date d'échéance: ").append(task.getDueDate()).append("\n")
                    .append("Statut: ").append(task.getStatus()).append("\n\n");
        }

        // Set the message of the dialog to the string representation of all tasks
        builder.setMessage(tasksStringBuilder.toString());

        // Add a button to dismiss the dialog
        builder.setPositiveButton("Fermer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Show the dialog
        builder.show();
    }

    private void updateTotalTaskCount() {
        int totalTasks = todoList.size() + doingList.size() + doneList.size();
        TextView totalTasksTextView = findViewById(R.id.total_tasks);
        totalTasksTextView.setText("Total Tasks: " + totalTasks);
    }



}
