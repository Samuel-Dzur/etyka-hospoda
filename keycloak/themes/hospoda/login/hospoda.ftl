<#macro backLink>
    <#assign homeUrl = properties.homeUrl>
    <#if client.baseUrl?has_content>
        <#assign homeUrl = client.baseUrl + "/login">
    </#if>
    <a class="hospoda-back-link" href="${homeUrl}">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true" focusable="false">
            <path d="m12 19-7-7 7-7"/>
            <path d="M19 12H5"/>
        </svg>
        <span>${msg("backToHome")}</span>
    </a>
</#macro>
