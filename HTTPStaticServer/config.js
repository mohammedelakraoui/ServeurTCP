{
	"port" : 9876,
	"hosts" : [{
		"name" : "mon-site.com",
		"document_root" : "/www/site1.com",
		"handlers" : [ {
			"clazz" : "ManagerFiles.HandlerFiles",
			"pattern" : "^.*$"
		}
		]
	},{
		"name" : "un-site.org",
		"document_root" : "C:/wamp/www/",
		"handlers" : [ {
			"clazz" : "ManagerFiles.HandlerFiles",
			"pattern" : "^.*$"
		}]
	}]
}