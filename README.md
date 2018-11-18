Movie Database Application

Statement of Goals: The user can enter the name of the movie and the app 
will return the requested information (i.e. films, biographical 
information, etc.) The app retrieves the information the open-source 
Open Movie Database (OMDb), a free movie information database.

Functional Description: The UI will be relatively simple and 
self-explanatory for the user. When the application opens the user is 
presented with a text box (for entering the movie name) and several 
check boxes for the type of information requested. The user enters the 
name of the movie, selects from several checkboxes the data that they 
want returned (i.e., “Year”, “Actors”, “Director”, etc). Pressing the 
search button results in the information queried from the OMDb and 
displayed for the user.

Milestones:

1. Initial UI design.
2. Establish connectivity to db.
3. Db responds to queries.
4. Full functionality.

Technical Breakdown:

1. User enters the movie title.
2. getAPISearchString() converts the movie title into a OMDb search 
string URL.
3. Creates a URL connection to the database and opens the connection.
4. Creates an input stream to the data file
5. Writes to the data file in JSON format for eventual parsing
