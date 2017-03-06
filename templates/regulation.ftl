<#--
<#setting name=value>
-->
<#macro print_elements elements>
	<#if elements?size != 0>
		<#list elements as element>
			<#if element.name == "meta">
					<#if element.rel?length != 0>
						<meta id="${element.id}" about="${element.about}" rel="${element.rel}" href="${element.href}" />
					<#else>
						<meta id="${element.id}" about="${element.about}" property="${element.property}" content="${element.content}" />
					</#if>
			<#else />	
				<${element.name} id="${element.id}">
					<#if element.name == "provision" || element.name == "title" || element.name == "heading" || element.name == "index">
						${element.text}
					</#if>
					<@print_elements element.elements />
				</${element.name}>
			</#if>
		</#list>
	</#if>
</#macro>
<#compress>
<#escape x as x?xml> 
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/css" href="../templates/regulation.css"?>

<regulation
	<#-- 
	<#assign h = {"name":"mouse", "price":50}>
	<#assign keys = h?keys>
	<#list keys as key>${key} = ${h[key]}; </#list>  
	-->
	<#list act.namespaces as namespace>
		<#if namespace.prefix == "">
			xmlns="${namespace.url}"
		<#else>
			xmlns:${namespace.prefix}="${namespace.url}"
		</#if>
	</#list>
    xsi:schemaLocation="http://informatika.ftn.uns.ac.rs/legal/metalex/1.0/strict regulation-strict.xsd" type="${act.type}" subtype="${act.subtype}"
    id="${act.id}" LocalNamingConventionMethod="${act.localNamingConventionMethod}" LocalNamingConventionScope="${act.localNamingConventionScope}" 
    naming="${act.naming}" xml:base="${act.base}"
    xmlns:grddl="http://www.w3.org/2003/g/data-view#" grddl:transformation="RDFa2RDFXML.xsl http://ns.inria.fr/grddl/rdfa/2008/09/03/RDFa2RDFXML.xsl" >
    
    <@print_elements act.elements />
 
</regulation>

</#escape> 

</#compress>