<#include "base.ftl">

<#macro main_content>

<#if book??>
    <h1>${book.title}</h1>
    <p>Author: ${book.author}</p>
    <p>Pages: ${book.numPages}</p>
<#else>
    <p>Book not found!</p>
</#if>

</#macro>

<@whole_page/>
