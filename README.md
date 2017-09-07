# Dropwizard sample project

To try it from IntelliJ IDEA:

1. First, `git clone` the project.
1. Go to menu _File, Open_. Select the `pom.xml` file and open as project.
1. Run the `MyApp` class (it will display a message in the console).
1. Go to _Edit Configurations..._
(drop-down menu you see at the top-right)
and in the `MyApp` configuration write `server src/main/resources/my-app.yaml`
in the `Program arguments` input box. Save with _OK_.
1. Run the `MyApp` configuration (from the top-right green play button).
The server will start in a couple of seconds
(you'll see something like `org.eclipse.jetty.server.Server: Started @3051ms` in the console).
1. You may open [http://localhost:8080/books](http://localhost:8080/books)
in your browser to see the output of
[BookResource.viewBooks()](https://github.com/fmaylinch/dropwizard-sample/blob/master/src/main/java/com/codethen/dropwizard/sample/resources/BookResource.java#L29).     

If you change anything, restart the server and refresh your browser page.

**Note about using this project as a starting point**

If you cloned the project and you want to make changes, you may want to
remove the `origin` remote by executing `git remote rm origin`.
After doing this, the repository you cloned won't be linked to my account
so you can upload the repository to your GitHub account as usual.

Another option is to [fork](https://help.github.com/articles/fork-a-repo/) the project before cloning it.
That way you will have a copy of the project in your GitHub account. 
