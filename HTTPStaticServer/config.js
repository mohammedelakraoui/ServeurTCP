{
	"port" : 9876,
	"hosts" : [{
		"name" : "mon-site.com",
		"document_root" : "/www/site1.com",
		"handlers" : [ {
			"clazz" : "Test.MyHandlerTest",
			"pattern" : "^.*$"
		}
		]
	},{
		"name" : "un-site.org",
		"document_root" : "C:/www/",
		"handlers" : [ {
			"clazz" : "ManagerFiles.HandlerFiles",
			"pattern" : "^.*$"
		}]
	}]
}