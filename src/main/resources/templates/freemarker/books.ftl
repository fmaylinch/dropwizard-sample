<html>
<head>
    <link rel="stylesheet" href="/assets/css/style.css">
</head>
<body>

<h1>Recommended books</h1>
<#list books>
<ul>
    <#items as book>
        <li><a href='/books/${book.id}'>${book.title}</a></li>
    </#items>
</ul>
<#else>
<div>No books to show!</div>
</#list>

<div class="footer">
    Template rendered with <a href="http://freemarker.org" target="_blank">FreeMarker</a>
</div>

</body>
</html>
