# Dropwizard sample project

To try it from IntelliJ IDEA:

1. First, `git clone` the project.
1. Go to menu _File, Open_. Select the `pom.xml` file and open as project.
1. Run the `MyApp` class (it will display a message in the console).
1. Go to _Edit Configurations..._
(drop-down menu you see at the top-right)
and in the `MyApp` configuration write `server`
in the `Program arguments` input box. Save with _OK_.
1. Run the `MyApp` configuration (from the top-right green play button).
The server will start in a couple of seconds
(you'll see something like `org.eclipse.jetty.server.Server: Started @3051ms` in the console).
1. You may open [http://localhost:8080/books](http://localhost:8080/books)
in your browser to see the output of
[BookResource.viewBooks()](https://github.com/fmaylinch/dropwizard-sample/blob/master/src/main/java/com/codethen/dropwizard/sample/resources/BookResource.java#L29).     

If you change anything, restart the server and refresh your browser page.

