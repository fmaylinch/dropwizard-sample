
<#--
    Include this template in the specific templates and
    override the main_content macro
-->

<#macro main_content>
<p>Replace this macro in specific templates</p>
</#macro>

<#macro whole_page>
<html>
<head>
    <link rel="stylesheet" href="/assets/css/style.css">
</head>
<body>

<@main_content/>

<div class="footer">
    Template rendered with <a href="http://freemarker.org" target="_blank">FreeMarker</a>
</div>

</body>
</html>
</#macro>
