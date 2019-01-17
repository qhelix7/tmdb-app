To run the Kotlin server, open a terminal and navigate to the `webserver` directory.
Then run the following:
    ./gradlew run

To run the Vue frontend, open another terminal and navigate to the `webapp` directory.
Then run the following:
    npm install
    npm run serve


The above commands run the code locally in development mode rather than 
building and deploying for production.


I used TypeScript with Vue. The type-safety is nice even though it requires
interpolating some of the documentation and examples.


Unfortunately, I did not get any tests written for this exercise. While I do
not follow a test-driven approach as strictly as I would like, I do normally 
write good tests as I complete features of a project.

Given the time constraints and my lacking familiarity with the frameworks, the
end result is not as polished as I would like. Future improvements I would add
include:
 * Better styling -- especially to the search button and movie tiles.
 * Responsive CSS so that it looks good on a variety of screen sizes.
 * A little fade in and out or some animation would look nice.
 * An empty search should clear the movie list.
 * Backend and frontend need to handle missing information (like images).
 * Backend and frontend need to handle timeouts and errors.
 * Put the API key in a config file rather than hard-coding it.
 * Tests!


Anyway, this has been a fun exercise. Thank you for your consideration!