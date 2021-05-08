# SelectableRecyclerView

## An android library developed to help developers select and process items stored in an ArrayList and presented in a RecyclerView.

The library makes it easy to

* Set the selection status of the items 
* Get the selected or the unselected Items
* Remove the selected or unselected items from the arraylist
* Set background drawables for items according to their selection status

### Supports

| function                 | Paramenters        | returns   | Description                                                  |
| ------------------------ | ------------------ | --------- | ------------------------------------------------------------ |
| selectItem               | Object             | void      | Accepts  an item and sets it as selected                     |
| unSelectItem             | Object             | void      | Accepts an item and sets it as unSelected                    |
| selectItem               | int                | void      | Accepts the position of an item and sets it as selected      |
| unSelectItem             | int                | void      | Accepts the position of an item and sets it as unSelected    |
| isSelected               | Object             | boolean   | accepts an item and checks if it is selected                 |
| isSelected               | int                | boolean   | accepts the position of an item and checks if it is selected |
| getSelectedItems         | -                  | ArrayList | returns an ArrayList with the selected items                 |
| getUnselectedItems       | -                  | ArrayList | returns an ArrayList with the uSelected items                |
| unSelectAllItems         | -                  | void      | unSelects all items                                          |
| selectAllItems           | -                  | void      | selects all items                                            |
| getSelectedItemCount     | -                  | int       | returns the count of the selected items                      |
| onNoItemSelected         | -                  | void      | callback that is triggered when selectedItemCount goes from >0 to 0 |
| onAtLeastOneItemSelected | -                  | void      | callback that is triggered when selectedItemCount goes from 0 to >0 |
| checkIfAllItemsSelected  | -                  | boolean   | checks if All the Items are selected                         |
| removeSelectedItems      | -                  | void      | removes the selected items from the item ArrayList           |
| removeUnselectedItems    | -                  | void      | removes the unSelected items from the item ArrayList         |
| setItemsDrawable         | Drawable, Drawable | void      | sets the background drawables of the selected and the unSelected items |

### Basic Usage

The library is  developed so that it diverges from the classic RecyclerView-Adapter as little as possible. Let's implement a simple SelectableRecyclerView that presents an ArrayList of strings and the user can select-unselect those items by clicking one them and then delete the selected.  If you already know how to set up a recyclerView move on to the Adapter and drawables sections.

1. Create a new project

   * Select "Empty Activity" 
   * Name your app: SelectableRecyclerViewExample
   * Select package name and save location 
   * Select java as language

2. Import the library

   * Add jitpack.io in build.gradle => allprojects => repositories among everything else you might need.

     ```
     allprojects {
         repositories {
            
             maven { url 'https://jitpack.io' }
     
         }
     }
     ```

   * Add implementation in build.gradle(:app) among everything else you might need.

     ```
         dependencies {
         	implementation 'com.github.dagkalis:SelectableRecyclerView:0.1.0'
         }
     ```

   * Make sure to sync Gradle files

3. Make a simple a simple layout in res->layout called **string_view.xml** for the adapter to use to display the strings. The view should be like this: 

   ```
   <?xml version="1.0" encoding="utf-8"?>
   <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto"
       xmlns:tools="http://schemas.android.com/tools"
       android:layout_width="match_parent"
       android:layout_height="wrap_content">
   
       <TextView
           android:id="@+id/textViewString"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:layout_marginTop="16dp"
           android:layout_marginBottom="16dp"
           android:text="TextView"
           app:layout_constraintBottom_toBottomOf="parent"
           app:layout_constraintEnd_toEndOf="parent"
           app:layout_constraintStart_toStartOf="parent"
           app:layout_constraintTop_toTopOf="parent" />
   </androidx.constraintlayout.widget.ConstraintLayout>
   ```

4. Make a new class with the name Adapter

   This class will use the utilities of the library.

   *  Instead of extending the RecyclerView.Adapter extend the class  SelectableRecyclerViewAdapter. Declaration should look like this.

   ```
   public class Adapter extends SelectableRecyclerViewAdapter<Adapter.SelectableHolder> {
   }
   
   ```

   * Now create the nested class SelectableHolder the same way you would if you used the recyclerView.Adapter. 

     ```
     public class SelectableHolder extends RecyclerView.ViewHolder {
             TextView string;
             public SelectableHolder(@NonNull View itemView) {
                 super(itemView);
                 string = itemView.findViewById(R.id.textViewString);
             }
         }
     ```

   * Make a constructor that accepts an ArrayList of strings and the Activity context. Assign both values to class variables.

     ```
         ArrayList<String> strings;
         Context context;
     
         public Adapter(ArrayList<String> strings, Context context) {
             this.strings = strings;
             this.context = context;
         }
     ```

   * Override the necessary functions.

     * getItems: a functions that returns the items.

       ```
          @NonNull
           @Override
           protected ArrayList getItems() {
               return strings;
           }
       ```

     * onCreateViewHolder: same as classic RecyclerView.

       ```
       @NonNull
       @Override
       public SelectableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view = (LayoutInflater.from(context)).inflate(R.layout.string_view, parent, false);
               return new SelectableHolder(view);
           }
       ```
       
     * onBindSelectableViewHolder: put here the logic you would in onBindViewHolder. Also we decide that the user can select and unselect item just by clicking on it so we put the appropriate logic. We also put some toasts so the we keep track of the changes.

       ```
           @Override
           public void onBindSelectableViewHolder(@NonNull SelectableHolder holder, int position) {
               holder.string.setText(strings.get(position));
       
               holder.itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(isSelected(position)){
                           unSelectItem(position);
                           Toast.makeText(context, "unselected item " + position, Toast.LENGTH_SHORT).show();
                       }else{
                           selectItem(position);
                           Toast.makeText(context, "selected item " + position, Toast.LENGTH_SHORT).show();
                       }
                       notifyItemChanged(position);
       
                   }
               });
           }
       ```

        

5. Although the user can select items he can't know the selection status of each. So we make two xml drawables to differentiate between them. Make two xml files in drawable.

   * selected_item_background_drawable.xml

     ```
     <?xml version="1.0" encoding="utf-8"?>
     <shape xmlns:android="http://schemas.android.com/apk/res/android"
         android:shape="rectangle">
         <corners
             android:radius="10dp" />
         <solid
             android:color="@color/white"/>
         <stroke
             android:width="0.1mm"
             android:color="#575757" />
     </shape>
     ```

   * un_selected_item_background_drawable.xml

     ```
     <?xml version="1.0" encoding="utf-8"?>
     <shape xmlns:android="http://schemas.android.com/apk/res/android"
         android:shape="rectangle">
         <corners
             android:radius="10dp" />
         <solid
             android:color="#FFF"/>
         <stroke
             android:width="0.1mm"
             android:color="#B8B8B8" />
     </shape>
     ```

   * Go to the adapter and in the constructor set the drawables. Now our adapter constructor looks like this: 

     ```
         public Adapter(ArrayList<String> strings, Context context) {
             this.strings = strings;
             this.context = context;
             setItemsDrawable(ContextCompat.getDrawable(context, R.drawable.selected_item_background_drawable),
                     ContextCompat.getDrawable(context, R.drawable.un_selected_item_background_drawable));
     
         }
     
     ```

6. Inside activity_main.xml: 

   * Insert a button to remove selected Items

     ```
         <Button
             android:id="@+id/remove_selected_btn"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:text="Remove Selected"
             app:layout_constraintEnd_toEndOf="parent"
             app:layout_constraintTop_toTopOf="parent" />
     ```

   * A recyclerView to present the strings

     ```
         <androidx.recyclerview.widget.RecyclerView
             android:id="@+id/recyclerView"
             android:layout_width="0dp"
             android:layout_height="0dp"
             android:layout_marginStart="8dp"
             android:layout_marginLeft="8dp"
             android:layout_marginTop="8dp"
             android:layout_marginEnd="8dp"
             android:layout_marginRight="8dp"
             android:layout_marginBottom="8dp"
             app:layout_constraintBottom_toBottomOf="parent"
             app:layout_constraintEnd_toEndOf="parent"
             app:layout_constraintStart_toStartOf="parent"
             app:layout_constraintTop_toBottomOf="@+id/remove_selected_btn" />
             
     ```

7. Inside MainActivity.java

   * Make an ArrayList of strings and populate it with some of them.

     ```
         ArrayList<String> strings = new ArrayList<>();
         for(int i = 0; i < 10; i++){
             strings.add("String" + i);
         }
     ```

   * Set up the recyclerView

     ```
     RecyclerView recyclerView = findViewById(R.id.recyclerView);
             recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
             Adapter adapter = new Adapter(strings, MainActivity.this);
             recyclerView.setAdapter(adapter);
             
     ```

   * Setup the button so the that it removes the selected items

     ```
             Button deleteSelectedButton = findViewById(R.id.remove_selected_btn);
             deleteSelectedButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     adapter.removeSelectedItems();
                     adapter.notifyDataSetChanged();
                 }
             });
     
     ```

8. Run the app

#### The Whole Files

1. MainActivity.java

   ```
   public class MainActivity extends AppCompatActivity {
   
       @Override
       protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);
   
           ArrayList<String> strings = new ArrayList<>();
           for(int i = 0; i < 10; i++){
               strings.add("String" + i);
           }
   
           RecyclerView recyclerView = findViewById(R.id.recyclerView);
           recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
           Adapter adapter = new Adapter(strings, MainActivity.this);
           recyclerView.setAdapter(adapter);
   
   
           Button deleteSelectedButton = findViewById(R.id.remove_selected_btn);
           deleteSelectedButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   adapter.removeSelectedItems();
                   adapter.notifyDataSetChanged();
               }
           });
   
   
   
       }
   }
   ```

2. activity_main.xml


   ```
   <?xml version="1.0" encoding="utf-8"?>
   <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto"
       xmlns:tools="http://schemas.android.com/tools"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       tools:context=".MainActivity">
   
       <androidx.recyclerview.widget.RecyclerView
           android:id="@+id/recyclerView"
           android:layout_width="0dp"
           android:layout_height="0dp"
           android:layout_marginStart="8dp"
           android:layout_marginLeft="8dp"
           android:layout_marginTop="8dp"
           android:layout_marginEnd="8dp"
           android:layout_marginRight="8dp"
           android:layout_marginBottom="8dp"
           app:layout_constraintBottom_toBottomOf="parent"
           app:layout_constraintEnd_toEndOf="parent"
           app:layout_constraintStart_toStartOf="parent"
           app:layout_constraintTop_toBottomOf="@+id/remove_selected_btn" />
   
       <Button
           android:id="@+id/remove_selected_btn"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="Remove Selected"
           app:layout_constraintEnd_toEndOf="parent"
           app:layout_constraintTop_toTopOf="parent" />
   
   </androidx.constraintlayout.widget.ConstraintLayout>
   ```

3. Adapter.java

   ```
   public class Adapter extends SelectableRecyclerViewAdapter<Adapter.SelectableHolder> {
       ArrayList<String> strings;
       Context context;
   
       public Adapter(ArrayList<String> strings, Context context) {
           this.strings = strings;
           this.context = context;
           setItemsDrawable(ContextCompat.getDrawable(context, R.drawable.selected_item_background_drawable),
                   ContextCompat.getDrawable(context, R.drawable.un_selected_item_background_drawable));
   
       }
   
       @Override
       public void onBindSelectableViewHolder(@NonNull SelectableHolder holder, int position) {
           holder.string.setText(strings.get(position));
   
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(isSelected(position)){
                       unSelectItem(position);
                       Toast.makeText(context, "unselected item " + position, Toast.LENGTH_SHORT).show();
                   }else{
                       selectItem(position);
                       Toast.makeText(context, "selected item " + position, Toast.LENGTH_SHORT).show();
                   }
                   notifyItemChanged(position);
   
               }
           });
       }
   
       @NonNull
       @Override
       protected ArrayList getItems() {
           return strings;
       }
   
       @NonNull
       @Override
       public SelectableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           View view = (LayoutInflater.from(context)).inflate(R.layout.string_view, parent, false);
           return new SelectableHolder(view);
       }
   
       public class SelectableHolder extends RecyclerView.ViewHolder {
           TextView string;
           public SelectableHolder(@NonNull View itemView) {
               super(itemView);
               string = itemView.findViewById(R.id.textViewString);
           }
       }
   }
   ```
   
4. string_view.xml

   ```
   <?xml version="1.0" encoding="utf-8"?>
   <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto"
       xmlns:tools="http://schemas.android.com/tools"
       android:layout_width="match_parent"
       android:layout_height="wrap_content">
   
       <TextView
           android:id="@+id/textViewString"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:layout_marginTop="16dp"
           android:layout_marginBottom="16dp"
           android:text="TextView"
           app:layout_constraintBottom_toBottomOf="parent"
           app:layout_constraintEnd_toEndOf="parent"
           app:layout_constraintStart_toStartOf="parent"
           app:layout_constraintTop_toTopOf="parent" />
   </androidx.constraintlayout.widget.ConstraintLayout>
   ```
   
5. selected_item_background_drawable.xml

   ```
   <?xml version="1.0" encoding="utf-8"?>
   <shape xmlns:android="http://schemas.android.com/apk/res/android"
       android:shape="rectangle">
       <corners
           android:radius="10dp" />
       <solid
           android:color="#FFC6C6"/>
       <stroke
           android:width="0.1mm"
           android:color="#FF8585" />
   </shape>
   ```
   
6. un_selected_item_background_drawable.xml

   ```
   <?xml version="1.0" encoding="utf-8"?>
   <shape xmlns:android="http://schemas.android.com/apk/res/android"
       android:shape="rectangle">
       <corners
           android:radius="10dp" />
       <solid
           android:color="#FFF"/>
       <stroke
           android:width="0.1mm"
           android:color="#B8B8B8" />
   </shape>
   ```

   
