
<#include "base.ftl">

<#macro main_content>

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

</#macro>

<@whole_page/>
